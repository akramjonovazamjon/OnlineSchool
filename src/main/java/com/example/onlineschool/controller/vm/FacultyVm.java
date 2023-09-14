package com.example.onlineschool.controller.vm;

import java.util.List;

public record FacultyVm(Long id, String name, List<SubjectVm> subjects) {
}
