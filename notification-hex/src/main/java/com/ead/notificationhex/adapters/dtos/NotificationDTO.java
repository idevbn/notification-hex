package com.ead.notificationhex.adapters.dtos;

import com.ead.notificationhex.core.domain.enums.NotificationStatus;

import javax.validation.constraints.NotNull;

public class NotificationDTO {

    @NotNull
    private NotificationStatus notificationStatus;

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(final NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

}
