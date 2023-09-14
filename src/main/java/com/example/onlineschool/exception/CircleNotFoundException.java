package com.example.onlineschool.exception;

public class CircleNotFoundException extends RuntimeException {
    public CircleNotFoundException() {
        super("error.not_found.circle");
    }
}
