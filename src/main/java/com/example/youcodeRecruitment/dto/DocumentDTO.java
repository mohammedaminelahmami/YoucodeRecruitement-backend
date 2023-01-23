package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {
    private int id_document;
    private String type;
    private CandidateDTO candidate;
}
