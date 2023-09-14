package com.example.onlineschool.exception;

public class NewsExistException extends RuntimeException {
    public NewsExistException() {
        super("error.duplicate.news");
    }
}
