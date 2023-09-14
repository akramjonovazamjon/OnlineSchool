package com.example.onlineschool.dto.faculty;

import java.util.List;

public record CreateFaculty(String name, List<Long> subjectIds) {
}
