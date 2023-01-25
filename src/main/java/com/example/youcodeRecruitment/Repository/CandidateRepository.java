package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Comment;
import com.example.youcodeRecruitment.Entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
    Page<Candidate> findAll(Pageable pageable);
}