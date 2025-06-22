package com.example.racemania.exceptions;

import java.io.Serial;

public class FailedFileAccessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4L;

    public FailedFileAccessException(String message, Throwable cause) { super(message + cause);}
}

