package com.spring.clinicmedia.domain.model.enitity.user;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@AttributeOverride(
        name = "userId",
        column = @Column(name = "lab_id")
)
@Entity
@Table(name = "LABS")
public class Lab extends User {


}
