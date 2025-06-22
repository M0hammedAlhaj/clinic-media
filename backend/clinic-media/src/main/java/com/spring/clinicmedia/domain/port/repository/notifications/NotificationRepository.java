package com.spring.clinicmedia.domain.port.repository.notifications;

import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.port.repository.BaseRepository;

public interface NotificationRepository<T extends Notification> extends BaseRepository<T, Long> {


}
