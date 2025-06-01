package com.spring.clinicmedia.domain.model.enitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INSURANCES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Insurance {

    @Id
    @Column(name = "insurance_name")
    private String insuranceName;
}
