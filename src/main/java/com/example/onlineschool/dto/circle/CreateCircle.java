package com.example.onlineschool.dto.circle;

import org.springframework.web.multipart.MultipartFile;

public record CreateCircle(String name, String content, MultipartFile file) {
}
