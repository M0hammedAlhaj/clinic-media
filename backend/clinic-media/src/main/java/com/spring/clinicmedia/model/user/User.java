package com.spring.clinicmedia.model.user;

import com.spring.clinicmedia.model.Registration;
import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {


    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Column(name = "is_active")
    private boolean isActive;

}
