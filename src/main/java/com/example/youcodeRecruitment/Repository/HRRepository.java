package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Entity.HREntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HREntity, Integer> {

    HREntity findByEmail(String email);
}
