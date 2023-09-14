package com.example.onlineschool.exception;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException() {
        super("error.not_found.news");
    }
}
