package com.spring.clinicmedia.infrastructure.Jpa.notifcations;

import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationPatientJpa extends JpaRepository<NotificationPatient, Long> {
}
