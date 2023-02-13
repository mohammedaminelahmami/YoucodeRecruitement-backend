package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Skills;
import com.example.youcodeRecruitment.Repository.SkillsRepository;
import com.example.youcodeRecruitment.Request.SkillsRequest;
import com.example.youcodeRecruitment.dto.SkillsDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillsService {
    private final SkillsRepository skillsRepository;
    private final AuthService authService;
    private final IMapperDto<SkillsRequest, Skills> mapperSkills;
    private final IMapperDto<Skills, SkillsDTO> mapperSkillsDTO;


    public SkillsDTO getSkills() {
        Candidate candidate = authService.getAuthenticatedCandidate();
        Skills skills = skillsRepository.findByCandidate(candidate).orElseThrow(() -> new RuntimeException("Skills not found"));
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
            Optional<Skills> skills1 = skillsRepository.findByCandidate(candidate);
            if (skills1.isPresent()) {
                if (skills.getFrontend() != null && !skills.getFrontend().equals(""))
                    skills1.get().setFrontend(skills1.get().getFrontend() + ";" + skills.getFrontend());
                if (skills.getBackend() != null && !skills.getBackend().equals(""))
                    skills1.get().setBackend(skills1.get().getBackend() + ";" + skills.getBackend());
                if (skills.getOutil() != null && !skills.getOutil().equals(""))
                    skills1.get().setOutil(skills1.get().getOutil() + ";" + skills.getOutil());
                if (skills.getDb() != null && !skills.getDb().equals(""))
                    skills1.get().setDb(skills1.get().getDb() + ";" + skills.getDb());
                skills1.get().setCandidate(candidate);

                skillsRepository.save(skills1.get());
            } else {
                skills.setCandidate(candidate);
                skillsRepository.save(skills);
            }
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

    public void deleteSkillsById(int idSkill, String skill, String skillName) {
        Optional<Skills> skills = skillsRepository.findById(idSkill);
        if (skills.isPresent()) {
            switch (skillName) {
                case "frontend" -> {
                    String[] arr = Arrays.stream(skills.get().getFrontend().split(";")).filter((e) -> !e.equals(skill)).map(String::trim).toArray(String[]::new);
                    skills.get().setFrontend(String.join(";", arr));
                }
                case "backend" -> {
                    String[] arr = Arrays.stream(skills.get().getBackend().split(";")).filter((e) -> !e.equals(skill)).map(String::trim).toArray(String[]::new);
                    skills.get().setBackend(String.join(";", arr));
                }
                case "outil" -> {
                    String[] arr = Arrays.stream(skills.get().getOutil().split(";")).filter((e) -> !e.equals(skill)).map(String::trim).toArray(String[]::new);
                    skills.get().setOutil(String.join(";", arr));
                }
                case "db" -> {
                    String[] arr = Arrays.stream(skills.get().getDb().split(";")).filter((e) -> !e.equals(skill)).map(String::trim).toArray(String[]::new);
                    skills.get().setDb(String.join(";", arr));
                }
            }
            skillsRepository.save(skills.get());
        } else {
            throw new RuntimeException("Skills is null");
        }
    }
}
