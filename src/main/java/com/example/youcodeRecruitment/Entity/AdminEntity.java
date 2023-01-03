package com.example.youcodeRecruitment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admins", catalog = "youcoderecruitment")
public class AdminEntity extends UserParentClass{
}
