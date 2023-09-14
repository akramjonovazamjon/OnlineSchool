package com.example.onlineschool.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("error.not_found.employee");
    }
}
