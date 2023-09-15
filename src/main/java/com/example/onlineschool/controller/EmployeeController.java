package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.EmployeeVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.employee.CreateEmployee;
import com.example.onlineschool.entity.Employee;
import com.example.onlineschool.mapper.EmployeeMapper;
import com.example.onlineschool.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeMapper mapper;

    @PostMapping
    public ResponseData<EmployeeVm> create(
            @RequestParam(name = "fullName") String fullName,
            @RequestParam(name = "position") String position,
            @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "department") String department
    ) throws IOException {
        Employee employee = service.create(new CreateEmployee(fullName, position, phoneNumber, email, file, department));
        return ResponseData.of(mapper.asEmployeeVm(employee));
    }

    @GetMapping
    public ResponseData<List<EmployeeVm>> getAll(Pageable pageable, @Param(value = "department") String department) {
        List<Employee> employees = service.getAll(pageable, department);
        return ResponseData.of(mapper.asEmployeeList(employees));
    }

    @GetMapping("/{id}")
    public ResponseData<EmployeeVm> getById(@PathVariable Long id) {
        Employee employee = service.getById(id);
        return ResponseData.of(mapper.asEmployeeVm(employee));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
