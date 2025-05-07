package com.spring.clinicmedia.domain.factory;

import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.infrastructure.factory.Creator;

public interface UserFactory<U> extends Creator<User,U> {

    User create(U t);
}
