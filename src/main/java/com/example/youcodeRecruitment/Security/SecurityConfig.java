package com.example.youcodeRecruitment.Security;
import com.example.youcodeRecruitment.Entity.AdminEntity;
import com.example.youcodeRecruitment.Entity.CandidateEntity;
import com.example.youcodeRecruitment.Entity.HREntity;
import com.example.youcodeRecruitment.Repository.UserDao;
import com.example.youcodeRecruitment.Service.AdminService;
import com.example.youcodeRecruitment.Service.CandidateService;
import com.example.youcodeRecruitment.Service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * @author Mohammed Amine LAHMAMI
 **/

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private HRService hrService;
    @Autowired
    private AdminService adminService;

//    @Autowired
//    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDao userDao) {
//        this.jwtAuthFilter = jwtAuthFilter;
//        this.userDao = userDao;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
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
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return email->{
                System.out.println("ur in userDetailsService now :");

                CandidateEntity candidate = candidateService.find_user_by_email(email);
                System.out.println(candidate);
                if (candidate != null) {
                    System.out.println("user found successfully in candidate table :");
                        return new org.springframework.security.core.userdetails.User(
                            candidate.getEmail(),
                            candidate.getPassword(),
                            Collections.singleton(new SimpleGrantedAuthority("candidate")));
                }

                HREntity hr = hrService.find_user_by_email(email);

                if (hr != null) {
                    System.out.println("user found successfully in HR table :");
                    return new org.springframework.security.core.userdetails.User(
                            hr.getEmail(),
                            hr.getPassword(),
                            Collections.singleton(new SimpleGrantedAuthority("HR")));
                }

                AdminEntity admin = adminService.find_user_by_email(email);

                if (admin != null) {
                    System.out.println("user found successfully in HR table :");
                    return new org.springframework.security.core.userdetails.User(
                            admin.getEmail(),
                            admin.getPassword(),
                            Collections.singleton(new SimpleGrantedAuthority("admin")));
                }
                throw new  UsernameNotFoundException("user not found");
        };

    }
}