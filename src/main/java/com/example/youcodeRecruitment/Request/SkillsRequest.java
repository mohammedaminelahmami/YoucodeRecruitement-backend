package com.example.youcodeRecruitment.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillsRequest {
    @NotBlank(message = "Front End Skills is required")
    private String frontend_skills;

    @NotBlank(message = "Back End Skills is required")
    private String backend_skills;

    @NotBlank(message = "Database Skills is required")
    private String db_skills;

    @NotBlank(message = "Outil Skills is required")
    private String outil_skills;

}
