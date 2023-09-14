package com.example.onlineschool.dto.news;

import org.springframework.web.multipart.MultipartFile;

public record CreateNews(String title, String content, MultipartFile image, String option) {
}
