package com.spring.clinicmedia.domain.exception;

public class ResourcesAlreadyExists extends RuntimeException {

    public ResourcesAlreadyExists(Class c,String id) {
        super(String.format("%s already exists by %s ", c.getName(), id));
    }

}
