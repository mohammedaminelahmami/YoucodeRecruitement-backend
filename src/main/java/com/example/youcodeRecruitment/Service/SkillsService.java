package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Skills;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Repository.SkillsRepository;
import com.example.youcodeRecruitment.Request.SkillsRequest;
import com.example.youcodeRecruitment.dto.SkillsDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final CandidateRepository candidateRepository;
    private final AuthService authService;
    private final IMapperDto<SkillsRequest, Skills> mapperSkills;
    private final IMapperDto<Skills, SkillsDTO> mapperSkillsDTO;


    public SkillsDTO getSkills(int id) {
        Skills skills = skillsRepository.findById(id).orElseThrow(()->
                new RuntimeException("Skills not found"));
        return mapperSkillsDTO.convertToEntity(skills, SkillsDTO.class);
    }


    public void createSkills(SkillsRequest skillsRequest) {
        Skills skills = mapperSkills.convertToEntity(skillsRequest, Skills.class);
        if (skills != null) {
            // get the user
            Candidate candidate = authService.getAuthenticatedCandidate();
            if (candidate == null) {
                throw new RuntimeException("You don't have access to skills");
            }
            skills.setCandidate(candidate);
            skillsRepository.save(skills);
        } else {
            throw new RuntimeException("Skills is null");
        }
    }

    public void updateSkills(SkillsRequest skillsRequest) {
        Skills skills = mapperSkills.convertToEntity(skillsRequest, Skills.class);
        if (skills != null) {
            // get the user
            Candidate candidate = authService.getAuthenticatedCandidate();
            skills.setCandidate(candidate);
            skillsRepository.save(skills);
        } else {
            throw new RuntimeException("Skills is null");
        }
    }

    public void deleteSkillsById(int id) {
        Optional<Skills> skills = skillsRepository.findById(id);
        if (skills.isPresent()) {
            skillsRepository.delete(skills.get());
        } else {
            throw new RuntimeException("Skills is null");
        }
    }



}
