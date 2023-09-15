package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.EmployeeVm;
import com.example.onlineschool.dto.employee.CreateEmployee;
import com.example.onlineschool.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "dto.fullName")
    @Mapping(target = "position", source = "dto.position")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "department", source = "dto.department")
    Employee asNewEmployee(CreateEmployee dto, String imgUrl);

    EmployeeVm asEmployeeVm(Employee employee);

    List<EmployeeVm> asEmployeeList(List<Employee> employees);

}
