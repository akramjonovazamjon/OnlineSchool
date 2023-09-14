package com.example.onlineschool.exception;

public class FacultyExistException extends RuntimeException {
    public FacultyExistException() {
        super("error.duplicate.faculty");
    }
}
