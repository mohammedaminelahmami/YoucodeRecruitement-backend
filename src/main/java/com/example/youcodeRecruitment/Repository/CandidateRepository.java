package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Entity.UserParentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {

//    UserParentClass findByEmail(String email);
    CandidateEntity findByEmail(String email);
}

