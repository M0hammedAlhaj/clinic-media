package com.spring.clinicmedia.domain.repository.notifications;

import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.repository.BaseRepository;

public interface NotificationRepository<T extends Notification> extends BaseRepository<T, Long> {


}
