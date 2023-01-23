package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.HR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HRRepository extends JpaRepository<HR, Integer> {
    Optional<HR> findByEmail(String email);
}