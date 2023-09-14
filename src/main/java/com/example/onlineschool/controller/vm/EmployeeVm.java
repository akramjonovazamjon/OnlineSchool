package com.example.onlineschool.controller.vm;

public record EmployeeVm(
        Long id,
        String fullName,
        String position,
        String phoneNumber,
        String email,
        String imgUrl
) {
}
