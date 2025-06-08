package com.nitya.spring_security.exceptions;

public class NotFoundException  extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
