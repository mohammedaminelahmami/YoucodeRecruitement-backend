package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Notification;
import com.example.youcodeRecruitment.Repository.NotificationRepository;
import com.example.youcodeRecruitment.dto.NotificationDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final IMapperDto<Notification, NotificationDTO> mapperNotificationDTO;

    public NotificationDTO getNotification(int id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Notification not found"));
        return mapperNotificationDTO.convertToEntity(notification, NotificationDTO.class);
    }

    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public void notificationSeen(int id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Notification not found"));
        notification.set_read(true);
        notificationRepository.save(notification);
    }


    public void deleteNotificationById(int id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Notification not found"));
        notificationRepository.delete(notification);
    }
}
