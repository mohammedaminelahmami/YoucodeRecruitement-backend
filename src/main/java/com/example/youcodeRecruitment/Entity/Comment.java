package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment", catalog = "youcoderecruitment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id_comment;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "hr_id")
    private HR hr;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}