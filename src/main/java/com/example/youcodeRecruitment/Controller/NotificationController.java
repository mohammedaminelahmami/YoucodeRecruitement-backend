package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void readNotification(int id) {
        notificationService.notificationSeen(id);
    }


    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotification(int id) {
        notificationService.deleteNotificationById(id);
    }
}
