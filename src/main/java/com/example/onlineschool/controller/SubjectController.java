package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.SubjectVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.subject.CreateSubject;
import com.example.onlineschool.dto.subject.UpdateSubject;
import com.example.onlineschool.entity.Subject;
import com.example.onlineschool.mapper.SubjectMapper;
import com.example.onlineschool.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService service;
    private final SubjectMapper mapper;

    @PostMapping
    public ResponseData<SubjectVm> create(@RequestBody CreateSubject dto) {
        Subject subject = service.create(dto);
        return ResponseData.of(mapper.asSubjectVm(subject));
    }

    @GetMapping
    public ResponseData<List<SubjectVm>> getAll(Pageable pageable) {
        List<Subject> subjects = service.getAll();
        return ResponseData.of(mapper.asSubjectVmList(subjects));
    }

    @GetMapping("/{id}")
    public ResponseData<SubjectVm> getById(@PathVariable Long id) {
        Subject subject = service.getById(id);
        return ResponseData.of(mapper.asSubjectVm(subject));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateSubject dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
