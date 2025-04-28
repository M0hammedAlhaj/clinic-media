package com.spring.clinicmedia.model.user;

import jakarta.persistence.*;

@Entity
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long admin_id;


}
