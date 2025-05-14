package com.spring.clinicmedia.domain.exception;

public class UserAccountNotActivation extends RuntimeException {

    public UserAccountNotActivation(String message) {
        super(message);
    }
}
