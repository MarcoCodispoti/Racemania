package com.example.racemania.exceptions;

public class FailedLoginException extends RuntimeException {
    public FailedLoginException(String message) {
        super(message);
    }

}
