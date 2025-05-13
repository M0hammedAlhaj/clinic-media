package com.spring.clinicmedia.domain.exception;

import com.spring.clinicmedia.domain.model.UserType;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }

    public ResourcesNotFoundException(UserType userType, long id) {
        super(String.format("%s not found by Id : %d", userType, id));
    }
    public ResourcesNotFoundException(UserType userType, String message) {
        super(String.format("%s not found by Id : %s", userType, message));
    }

}
