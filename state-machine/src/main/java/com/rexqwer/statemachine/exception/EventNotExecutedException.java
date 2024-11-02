package com.rexqwer.statemachine.exception;

public class EventNotExecutedException extends RuntimeException {
    public EventNotExecutedException(String message) {
        super(message);
    }
}
