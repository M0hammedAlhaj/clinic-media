package com.spring.clinicmedia.model.user;

import com.spring.clinicmedia.model.Registration;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class User {


    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Column(name = "is_active")
    private boolean isActive;

}
