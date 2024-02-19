package com.commerzbank.library.exception;

public class BookIsRentedException extends RuntimeException {
    public BookIsRentedException(String message) {
        super(message);
    }
}
