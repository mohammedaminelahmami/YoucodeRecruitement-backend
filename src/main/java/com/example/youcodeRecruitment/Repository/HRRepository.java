package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.HR;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HRRepository extends JpaRepository<HR, Long> {
    Optional<HR> findByEmail(String email);
    Page<HR> findAll(Pageable pageable);
}