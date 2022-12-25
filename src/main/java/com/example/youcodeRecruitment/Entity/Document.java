package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToOne
    private CandidateEntity candidate;

    @OneToMany(mappedBy = "document")
    private List<Comment> comments;


}
