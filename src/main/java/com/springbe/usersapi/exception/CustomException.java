package com.springbe.usersapi.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
