package com.dhruv.translationapi.exception;

import org.springframework.http.HttpStatus;

public class TranslationException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public TranslationException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
