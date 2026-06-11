package org.example.mapper;

import org.example.model.dto.NotificationDto;
import org.example.model.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDto toDto(Notification notification) {
        if (notification == null) return null;

        return NotificationDto.builder()
                .title(notification.getTitle())
                .message(notification.getMessage())
                .channel(notification.getChannel())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .recipientId(notification.getRecipient() != null ? notification.getRecipient().getId() : null)
                .build();
    }

    public Notification toEntity(NotificationDto notificationDto) {
        if (notificationDto == null) return null;

        Notification notification = new Notification();
        notification.setTitle(notificationDto.getTitle());
        notification.setMessage(notificationDto.getMessage());
        notification.setChannel(notificationDto.getChannel());
        notification.setStatus(notificationDto.getStatus());
        notification.setCreatedAt(notificationDto.getCreatedAt());
        notification.setSentAt(notificationDto.getSentAt());
        return notification;
    }
}