package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "document", catalog = "youcoderecruitment")
public class Document {
    private enum Type {CV, MotivationLetter, Other};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private int id_document;

    @Column(name = "type")
    private Type type;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_at;

    @Column(name = "updated_at", updatable = false , insertable = false ,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private String updated_at;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToMany(mappedBy = "document")
    private Set<Comment> commentEntities;


}
