package com.example.youcodeRecruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private String body;
    private HRDTO hr;
    private DocumentDTO document;
}