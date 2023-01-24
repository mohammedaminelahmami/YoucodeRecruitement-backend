package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillsDTO {
    private String frontend_skills;
    private String backend_skills;
    private String db_skills;
    private String outil_skills;
    private CandidateDTO candidate;
}
