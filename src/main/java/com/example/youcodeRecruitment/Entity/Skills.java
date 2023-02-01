package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skills", catalog = "youcoderecruitment")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private int id_skill;

    @Column(name = "frontend")
    private String frontend;

    @Column(name = "backend")
    private String backend;

    @Column(name = "db")
    private String db;

    @Column(name = "outil")
    private String outil;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_at;

    @Column(name = "updated_at", updatable = false , insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private String updated_at;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}