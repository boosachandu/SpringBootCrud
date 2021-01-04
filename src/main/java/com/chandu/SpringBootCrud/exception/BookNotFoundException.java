package com.chandu.SpringBootCrud.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "BookNotFoundException{}";
    }
}
