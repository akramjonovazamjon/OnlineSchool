package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.CircleVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.circle.CreateCircle;
import com.example.onlineschool.entity.Circle;
import com.example.onlineschool.mapper.CircleMapper;
import com.example.onlineschool.service.CircleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/circles")
public class CircleController {

    private final CircleService service;
    private final CircleMapper mapper;

    @PostMapping
    public ResponseData<CircleVm> create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "file") MultipartFile file
    ) throws IOException {
        Circle circle = service.create(new CreateCircle(name, content, file));
        return ResponseData.of(mapper.asCircleVm(circle));
    }

    @GetMapping("/{id}")
    public ResponseData<CircleVm> getById(@PathVariable Long id) {
        Circle circle = service.getById(id);
        return ResponseData.of(mapper.asCircleVm(circle));
    }

    @GetMapping
    public ResponseData<List<CircleVm>> getAll(Pageable pageable) {
        List<Circle> circleList = service.getAll(pageable);
        return ResponseData.of(mapper.asCircleList(circleList));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
