package com.ead.notificationhex.core.ports;

import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationServicePort {

    NotificationDomain saveNotification(final NotificationDomain notificationDomain);

    List<NotificationDomain> findAllNotificationsByUser(final UUID userId, final PageInfo pageInfo);

    Optional<NotificationDomain> findByNotificationIdAndUserId(
            final UUID notificationId,
            final UUID userId
    );

}
