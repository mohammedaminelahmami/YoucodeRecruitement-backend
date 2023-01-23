package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}