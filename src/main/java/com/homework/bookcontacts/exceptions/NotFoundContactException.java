package com.homework.bookcontacts.exceptions;

public class NotFoundContactException extends RuntimeException {

    public NotFoundContactException(String message) {
        super(message);
    }
}
