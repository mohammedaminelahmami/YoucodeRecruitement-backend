package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skills, Integer> {
    Optional<Skills> findByCandidate(Candidate candidate);

}