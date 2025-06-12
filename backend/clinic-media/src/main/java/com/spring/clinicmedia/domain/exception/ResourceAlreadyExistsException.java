package com.spring.clinicmedia.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(Class<?> clazz, String identifier) {
        super(String.format("%s already exists with ID: %s", clazz.getSimpleName(), identifier));
    }

    public ResourceAlreadyExistsException(String resourceName, String identifier) {
        super(String.format("%s already exists with ID: %s", resourceName, identifier));
    }

    public ResourceAlreadyExistsException(String s) {
        super(s);
    }
}
