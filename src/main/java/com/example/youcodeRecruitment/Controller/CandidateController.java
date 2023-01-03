package com.example.youcodeRecruitment.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/Candidate")
public class CandidateController {

    @GetMapping
    public String helloCondidate(){
        return "hello condidate";
    }
}
