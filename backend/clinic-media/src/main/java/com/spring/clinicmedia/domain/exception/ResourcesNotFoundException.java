package com.spring.clinicmedia.domain.exception;

import com.spring.clinicmedia.domain.model.UserType;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }

    public ResourcesNotFoundException(UserType userType, Integer id) {
        super(String.format("%s not found by Id : %d", userType, id));
    }
}
