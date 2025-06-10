package com.spring.clinicmedia.domain.port.validator;

public interface SpecialityValidator {


    void validateSpecialityAssignment(Long userId, String specialityName);

}
