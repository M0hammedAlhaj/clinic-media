package com.spring.clinicmedia.domain.model.enitity.notifications;


import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "NOTIFICATION_PATIENTS")
@AttributeOverride(
        name = "notificationId",
        column = @Column(name = "notification_patient_id")
)
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NotificationPatient extends Notification {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
