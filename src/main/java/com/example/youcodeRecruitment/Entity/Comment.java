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
    private HREntity hr;

    @ManyToOne
    private Document document;
}