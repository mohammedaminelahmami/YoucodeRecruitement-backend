package com.example.youcodeRecruitment.provider;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
@Configuration
public class CloudinaryProvider {
    public String uploadFile(MultipartFile file)  {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxtd38f8u",
                "api_key", "766345328827476",
                "api_secret", "IafWAflzzIR6Wv07mvCXQYYx9pk"));

        Map uploadResult ;
        try {
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}