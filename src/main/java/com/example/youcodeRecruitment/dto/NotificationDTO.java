package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {

    private String message;
    private boolean is_read;
    private boolean is_deleted;
    private String created_at;
    private CandidateDTO candidate;  

}
