package com.ead.notificationhex.adapters.inbound.controllers;

import com.ead.notificationhex.adapters.dtos.NotificationDTO;
import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;
import com.ead.notificationhex.core.ports.NotificationServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {

    private final NotificationServicePort notificationServicePort;

    public UserNotificationController(final NotificationServicePort notificationServicePort) {
        this.notificationServicePort = notificationServicePort;
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping("users/{userId}/notifications")
    public ResponseEntity<Page<NotificationDomain>> getAllNotificationsByUser(
            @PathVariable(value = "userId") final UUID userId,
            @PageableDefault(
                    page = 0, size = 10, sort = "notificationId",
                    direction = Sort.Direction.ASC
            ) final Pageable pageable
            ) {
        final PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);

        final List<NotificationDomain> notificationsDomainList = this.notificationServicePort
                .findAllNotificationsByUser(userId, pageInfo);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl<>(notificationsDomainList, pageable, notificationsDomainList.size()));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PutMapping("users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> updateNotification(
            @PathVariable(value = "userId") final UUID userId,
            @PathVariable(value = "notificationId") final UUID notificationId,
            @RequestBody @Valid final NotificationDTO notificationDTO
    ) {
        final Optional<NotificationDomain> notificationModelOpt = this.notificationServicePort
                .findByNotificationIdAndUserId(notificationId, userId);

        if (notificationModelOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found!");
        }

        notificationModelOpt.get().setNotificationStatus(notificationDTO.getNotificationStatus());

        final NotificationDomain notificationDomain = this.notificationServicePort
                .saveNotification(notificationModelOpt.get());

        return ResponseEntity.status(HttpStatus.OK).body(notificationDomain);
    }

}
