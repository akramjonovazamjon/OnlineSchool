package com.example.onlineschool.exception;

public class FacultyNotFoundException extends RuntimeException {
    public FacultyNotFoundException() {
        super("error.not_found.faculty");
    }
}
