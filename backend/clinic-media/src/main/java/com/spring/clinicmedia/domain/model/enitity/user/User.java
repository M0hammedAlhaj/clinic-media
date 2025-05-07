package com.spring.clinicmedia.domain.model.enitity.user;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import jakarta.persistence.*;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Column(name = "is_active")
    private boolean isActive;

}
