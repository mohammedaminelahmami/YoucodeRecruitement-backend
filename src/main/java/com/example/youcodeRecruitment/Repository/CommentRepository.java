package com.example.youcodeRecruitment.Repository;

import com.example.youcodeRecruitment.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByDocument(Long id, Pageable pageable);
}