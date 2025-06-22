package com.example.racemania.exceptions;

import java.io.Serial;

public class FailedInsertException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public FailedInsertException (String message){
        super(message);
    }
    public FailedInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
