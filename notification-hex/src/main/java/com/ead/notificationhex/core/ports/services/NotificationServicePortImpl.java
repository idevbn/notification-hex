package com.ead.notificationhex.core.ports.services;

import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;
import com.ead.notificationhex.core.domain.enums.NotificationStatus;
import com.ead.notificationhex.core.ports.NotificationPersistencePort;
import com.ead.notificationhex.core.ports.NotificationServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServicePortImpl implements NotificationServicePort {

    private final NotificationPersistencePort notificationPersistencePort;

    public NotificationServicePortImpl(final NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }

    @Override
    public NotificationDomain saveNotification(final NotificationDomain NotificationDomain) {
        return this.notificationPersistencePort.save(NotificationDomain);
    }

    @Override
    public List<NotificationDomain> findAllNotificationsByUser(
            final UUID userId,
            final PageInfo pageInfo
    ) {
        return this.notificationPersistencePort
                .findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageInfo);
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(
            final UUID notificationId,
            final UUID userId
    ) {
        return this.notificationPersistencePort.findByNotificationIdAndUserId(notificationId, userId);
    }

}
