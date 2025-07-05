package com.spring.clinicmedia.infrastructure.Jpa.notifcations;

import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationClinic;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationClinicJpa extends JpaRepository<NotificationClinic, Long> {
}
