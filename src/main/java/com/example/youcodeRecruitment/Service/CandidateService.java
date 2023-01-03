package com.example.youcodeRecruitment.Service;


import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CandidateService  {


    @Autowired
    private CandidateRepository candidateRepository;
    public CandidateEntity find_user_by_email(String email) {
        return candidateRepository.findByEmail(email);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("ur in loadUserByUsername <CandidateService> now :");
//        System.out.println("email :"+ email);
//        return null;
//    }
}
