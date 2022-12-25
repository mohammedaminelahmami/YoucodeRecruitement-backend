package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hr", catalog = "youcoderecruitment")
public class HREntity extends UserParentClass {
    @OneToMany(mappedBy = "hr")
    private List<Comment> comments;
}