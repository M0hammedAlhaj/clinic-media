package com.spring.clinicmedia.domain.model.enitity;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(
        name = "clinic_doctor_requests",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"clinic_id", "doctor_id"}))
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDoctorRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false, length = 20)
    private RequestStatus status = RequestStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "sender", nullable = false, length = 10)
    private UserType sender;

    @PrePersist
    public void setDefaults() {
        if (status == null) {
            status = RequestStatus.PENDING;
        }
    }
}
