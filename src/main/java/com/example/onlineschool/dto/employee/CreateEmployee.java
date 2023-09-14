package com.example.onlineschool.dto.employee;

import org.springframework.web.multipart.MultipartFile;

public record CreateEmployee(
        String fullName,
        String position,
        String phoneNumber,
        String email,
        MultipartFile file
) {
}
