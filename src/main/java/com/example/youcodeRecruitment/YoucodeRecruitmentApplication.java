package com.example.youcodeRecruitment;

import com.example.youcodeRecruitment.Service.CandidatService;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.modelmapper.ModelMapper;

import java.util.List;


@SpringBootApplication
public class YoucodeRecruitmentApplication {


    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(YoucodeRecruitmentApplication.class, args);
    }


}
