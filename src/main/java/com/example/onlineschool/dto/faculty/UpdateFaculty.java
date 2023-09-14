package com.example.onlineschool.dto.faculty;

import java.util.List;

public record UpdateFaculty(String name, List<Long> subjectIds) {
}
