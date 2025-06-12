package com.spring.clinicmedia.application.insurance.users;

import com.spring.clinicmedia.domain.model.enitity.user.User;

public interface UserInsuranceAdder {

    User addInsurance(String insuranceName, Long userId);

}
