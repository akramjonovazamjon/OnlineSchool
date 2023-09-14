package com.example.onlineschool.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
        super("error.not_found.subject");
    }
}
