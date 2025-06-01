package com.spring.clinicmedia.domain.exception.request;

public class RequestStatusChangeNotAllowedException extends RuntimeException {
    public RequestStatusChangeNotAllowedException(String message) {
        super(message);
    }
}
