package com.example.onlineschool.exception;

public class PartnerExistException extends RuntimeException {
    public PartnerExistException() {
        super("error.duplicate.partner");
    }
}
