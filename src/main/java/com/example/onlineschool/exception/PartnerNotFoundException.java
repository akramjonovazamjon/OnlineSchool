package com.example.onlineschool.exception;

public class PartnerNotFoundException extends RuntimeException {
    public PartnerNotFoundException() {
        super("error.not_found.partner");
    }
}
