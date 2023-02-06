package com.example.youcodeRecruitment.Security;

import com.example.youcodeRecruitment.Repository.AdminRepository;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Repository.HRRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

    private final AdminRepository adminRepository;
    private final CandidateRepository candidateRepository;
    private final HRRepository hrRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            if (adminRepository.findByEmail(username).isPresent()){
                return adminRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("admin not found"));
            }else if (hrRepository.findByEmail(username).isPresent()){
                return hrRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("HR not found"));
            }else if (candidateRepository.findByEmail(username).isPresent()){
                return candidateRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Candidate not found"));
            }else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authProvider(){
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
