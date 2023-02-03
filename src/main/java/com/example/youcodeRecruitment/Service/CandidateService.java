package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Request.CandidateRequest;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final IMapperDto<CandidateDTO, Candidate> mapperDTO;
    private final UploadFileService uploadFileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void updateCandidate(CandidateRequest candidateRequest, Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if (candidate.isPresent()) {
            boolean checkCurrentPassword = bCryptPasswordEncoder.matches(candidateRequest.getPassword(), candidate.get().getPassword());
            if (checkCurrentPassword) {
                candidate.get().setFirstname(candidateRequest.getFirstName());
                candidate.get().setLastname(candidateRequest.getLastName());
                candidate.get().setEmail(candidateRequest.getEmail());
                candidateRepository.save(candidate.get());
            } else {
                throw new RuntimeException("current password incorrect");
            }
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }

    public void updateCandidateImage(Long id, MultipartFile imageFile) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if(candidate.isPresent()) {
            String path = uploadFileService.getPath(imageFile); // save the image
            candidate.get().setImage(path); // set the image path
            candidateRepository.save(candidate.get());
        }else{
            throw new RuntimeException("Candidate not found");
        }
    }

    public void deleteCandidate(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            candidateRepository.delete(candidate.get());
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }

    public CandidateDTO getOneCandidate(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return mapperDTO.convertToDTO(candidate.get(), CandidateDTO.class);
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }

    public List<CandidateDTO> getAllCandidates(int page, int limit) {
        if (page > 0) page--;
        List<Candidate> candidates = candidateRepository.findAll(PageRequest.of(page, limit)).getContent();
        return mapperDTO.convertListToListDto(candidates, CandidateDTO.class);
    }
}

