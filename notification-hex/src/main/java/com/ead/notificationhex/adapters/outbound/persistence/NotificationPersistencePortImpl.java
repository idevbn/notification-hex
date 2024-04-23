package com.ead.notificationhex.adapters.outbound.persistence;

import com.ead.notificationhex.adapters.outbound.persistence.entities.NotificationEntity;
import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;
import com.ead.notificationhex.core.domain.enums.NotificationStatus;
import com.ead.notificationhex.core.ports.NotificationPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class NotificationPersistencePortImpl implements NotificationPersistencePort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final ModelMapper modelMapper;

    public NotificationPersistencePortImpl(final NotificationJpaRepository notificationJpaRepository,
                                           final ModelMapper modelMapper) {
        this.notificationJpaRepository = notificationJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificationDomain save(final NotificationDomain notificationDomain) {
        final NotificationEntity notificationEntity = this.notificationJpaRepository
                .save(modelMapper.map(notificationDomain, NotificationEntity.class));

        return this.modelMapper.map(notificationEntity, NotificationDomain.class);
    }

    @Override
    public List<NotificationDomain> findAllByUserIdAndNotificationStatus(
            final UUID userId,
            final NotificationStatus notificationStatus,
            final PageInfo pageInfo
    ) {
        final Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());

        return this.notificationJpaRepository
                .findAllByUserIdAndNotificationStatus(userId, notificationStatus, pageable).stream()
                .map(entity -> modelMapper.map(entity, NotificationDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(final UUID notificationId,
                                                                      final UUID userId) {
        final Optional<NotificationEntity> notificationEntityOptional = notificationJpaRepository
                .findByNotificationIdAndUserId(notificationId, userId);

        if (notificationEntityOptional.isPresent()) {
            return Optional
                    .of(modelMapper.map(notificationEntityOptional.get(), NotificationDomain.class));
        }

        return Optional.empty();
    }

}
