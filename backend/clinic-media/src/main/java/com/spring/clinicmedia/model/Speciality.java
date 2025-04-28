package com.spring.clinicmedia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SPECIALITY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {

    @Id
    @Column(name = "speciality_name")
    private String specialityName;
}
