package com.example.onlineschool.exception;

public class ClientUserNotFoundException extends RuntimeException {
    public ClientUserNotFoundException() {
        super("error.not_found.client_user");
    }
}
