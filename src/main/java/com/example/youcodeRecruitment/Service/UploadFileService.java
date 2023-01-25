package com.example.youcodeRecruitment.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.youcodeRecruitment.provider.CloudinaryProvider;

@AllArgsConstructor
@Service
public class UploadFileService {

    private final CloudinaryProvider cloudinaryProvider;

    public String getPath(MultipartFile file) throws RuntimeException {
        return cloudinaryProvider.uploadFile(file);
    }
}
