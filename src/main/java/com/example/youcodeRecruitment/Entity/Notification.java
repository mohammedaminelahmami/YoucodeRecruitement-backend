package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification", catalog = "youcoderecruitment")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private int id_notification;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;


}