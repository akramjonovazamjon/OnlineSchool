package com.example.onlineschool.controller.vm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record NewsVm(
        Long id,
        String title,
        String content,
        String imgUrl,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        LocalDateTime time,
        String option
) {
}
