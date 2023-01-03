package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.AdminEntity;
import com.example.youcodeRecruitment.Entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByEmail(String email);

}
