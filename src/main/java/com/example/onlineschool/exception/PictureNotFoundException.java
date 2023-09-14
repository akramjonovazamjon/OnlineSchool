package com.example.onlineschool.exception;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException() {
        super("error.not_found.picture");
    }
}
