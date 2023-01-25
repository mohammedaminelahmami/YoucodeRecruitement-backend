package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
 //
}
