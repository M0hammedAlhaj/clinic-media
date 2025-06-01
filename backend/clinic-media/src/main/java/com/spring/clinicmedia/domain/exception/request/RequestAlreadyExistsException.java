package com.spring.clinicmedia.domain.exception.request;

public class RequestAlreadyExistsException extends RuntimeException {
    public RequestAlreadyExistsException(String message) {
        super(message);
    }
}
