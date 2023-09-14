package com.example.onlineschool.exception;

public class EmployeeExistException extends RuntimeException {
    public EmployeeExistException() {
        super("error.duplicate.employee");
    }
}
