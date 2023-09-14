package com.example.onlineschool.dto.partner;

import org.springframework.web.multipart.MultipartFile;

public record CreatePartner(String name, MultipartFile file) {
}
