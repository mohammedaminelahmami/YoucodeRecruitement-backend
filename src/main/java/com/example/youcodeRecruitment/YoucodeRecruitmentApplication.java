package com.example.youcodeRecruitment;

import com.example.youcodeRecruitment.Entity.Admin;
import com.example.youcodeRecruitment.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class YoucodeRecruitmentApplication {

    @Autowired
    AdminRepository adminRepository;

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            for(int i = 1; i < 3; i++) {
                Admin admin = new Admin();
                admin.setFirstname("admin" + i);
                admin.setLastname("admin" + i);
                admin.setEmail("admin" + i + "@gmail.com");
                admin.setPassword(bCryptPasswordEncoder().encode("admin" + i));
                adminRepository.save(admin);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(YoucodeRecruitmentApplication.class, args);
    }


}
