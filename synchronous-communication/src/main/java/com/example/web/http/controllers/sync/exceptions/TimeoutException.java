package com.example.web.http.controllers.sync.exceptions;

public class TimeoutException extends RuntimeException {
    public TimeoutException(String message, java.util.concurrent.TimeoutException ex) {
        super(message);
    }
}
