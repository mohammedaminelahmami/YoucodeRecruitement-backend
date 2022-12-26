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

    @Column(name = "frontend_skills")
    private String frontend_skills;

    @Column(name = "backend_skills")
    private String backend_skills;

    @Column(name = "db_skills")
    private String db_skills;

    @Column(name = "outil_skills")
    private String outil_skills;

    @OneToOne
    private CandidateEntity candidate;
}