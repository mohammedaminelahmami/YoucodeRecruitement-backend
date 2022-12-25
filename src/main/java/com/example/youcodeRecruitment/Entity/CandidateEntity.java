package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "candidate", catalog = "youcoderecruitment")
public class CandidateEntity extends UserParentClass {

    @OneToMany(mappedBy = "candidate")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "candidate")
    private List<Document> documents;

    @OneToOne
    private Skills skills;
}