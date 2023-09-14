package com.example.onlineschool.exception;

public class SubjectExistException extends RuntimeException {
    public SubjectExistException() {
        super("error.duplicate.subject");
    }
}
