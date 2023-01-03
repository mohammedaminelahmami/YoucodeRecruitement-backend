package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Entity.HREntity;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Repository.HRRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class HRService {

        @Autowired
        private HRRepository hrRepository;
        public HREntity find_user_by_email(String email) {
            return hrRepository.findByEmail(email);
        }
}
