package com.chandu.SpringBootCrud.exception;

public class APIException extends RuntimeException{

    public APIException(String message) {
        super(message);
    }
}
