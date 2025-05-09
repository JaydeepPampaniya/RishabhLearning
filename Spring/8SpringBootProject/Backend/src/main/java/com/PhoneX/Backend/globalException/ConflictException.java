package com.PhoneX.Backend.globalException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
