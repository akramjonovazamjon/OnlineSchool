package com.example.onlineschool.exception;

public class CircleExistException extends RuntimeException {
    public CircleExistException() {
        super("error.duplicate.circle");
    }
}
