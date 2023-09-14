package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.FacultyVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.faculty.CreateFaculty;
import com.example.onlineschool.dto.faculty.UpdateFaculty;
import com.example.onlineschool.entity.Faculty;
import com.example.onlineschool.mapper.FacultyMapper;
import com.example.onlineschool.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService service;
    private final FacultyMapper mapper;

    @PostMapping
    public ResponseData<FacultyVm> create(@RequestBody CreateFaculty dto) {
        Faculty faculty = service.create(dto);
        return ResponseData.of(mapper.asFacultyVm(faculty));
    }

    @GetMapping("/{id}")
    public ResponseData<FacultyVm> getById(@PathVariable Long id) {
        Faculty faculty = service.getById(id);
        return ResponseData.of(mapper.asFacultyVm(faculty));
    }

    @GetMapping
    public ResponseData<List<FacultyVm>> getAll(Pageable pageable) {
        List<Faculty> faculties = service.getAll(pageable);
        return ResponseData.of(mapper.asFacultyVmList(faculties));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateFaculty dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
