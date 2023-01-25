package com.example.youcodeRecruitment.Controller;


import com.example.youcodeRecruitment.Request.SkillsRequest;
import com.example.youcodeRecruitment.Service.SkillsService;
import com.example.youcodeRecruitment.dto.SkillsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid SkillsRequest skillsRequest) {
        skillsService.createSkills(skillsRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SkillsDTO getSkills(@PathVariable int id) {
        return skillsService.getSkills(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSkills(@PathVariable int id, @RequestBody @Valid SkillsRequest skillsRequest) {
        skillsService.updateSkills(skillsRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkills(@PathVariable int id) {
        skillsService.deleteSkillsById(id);
    }




}
