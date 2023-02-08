package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillsDTO {
    private int id_skill;
    private String frontend;
    private String backend;
    private String db;
    private String outil;
    private CandidateDTO candidate;
}
