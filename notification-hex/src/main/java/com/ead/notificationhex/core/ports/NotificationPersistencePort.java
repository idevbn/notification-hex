package com.ead.notificationhex.core.ports;

import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;
import com.ead.notificationhex.core.domain.enums.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationPersistencePort {

    NotificationDomain save(final NotificationDomain notificationDomain);

    List<NotificationDomain> findAllByUserIdAndNotificationStatus(
            final UUID userId,
            final NotificationStatus notificationStatus,
            final PageInfo pageInfo
            );

    Optional<NotificationDomain> findByNotificationIdAndUserId(
            final UUID notificationId,
            final UUID userId
    );

}
