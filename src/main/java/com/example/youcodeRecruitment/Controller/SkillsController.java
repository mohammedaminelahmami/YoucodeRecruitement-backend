package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.SkillsRequest;
import com.example.youcodeRecruitment.Service.SkillsService;
import com.example.youcodeRecruitment.dto.SkillsDTO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid SkillsRequest skillsRequest) {
        skillsService.createSkills(skillsRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SkillsDTO getSkills() {
        return skillsService.getSkills();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSkills(@PathVariable int id, @RequestBody @Valid SkillsRequest skillsRequest) {
        skillsService.updateSkills(skillsRequest);
    }

    @PostMapping("/update-skills")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSkills(@RequestParam("idSkill") String idSkill, @RequestParam("skill") String skill, @RequestParam("skillName") String skillName) {
        skillsService.deleteSkillsById(Integer.parseInt(idSkill), skill, skillName);
    }

}
