package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateDTO {
    private int id_user;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private Boolean status;
}
