package com.example.youcodeRecruitment.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message = "BAD REQUEST";
    private int status = 400;
}