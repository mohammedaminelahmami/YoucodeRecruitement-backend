package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Request.CandidateRequest;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    public void updateCandidate(String firstName, String lastName, String email, String password, MultipartFile imageFile, Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        boolean checkNull = firstName != null && lastName != null && email != null && password != null && imageFile != null;
        boolean checkEmpty = !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !imageFile.isEmpty();
        if(checkNull && checkEmpty) {
            if(candidate.isPresent()) {
                candidate.get().setFirstname(firstName);
                candidate.get().setLastname(lastName);
                candidate.get().setEmail(email);
                candidate.get().setPassword(password);
                String path = uploadFileService.getPath(imageFile); // save the image
                candidate.get().setImage(path); // set the image path
                candidateRepository.save(candidate.get());
            }else{
                throw new RuntimeException("Candidate not found");
            }
        }else{
            throw new RuntimeException("fields are empty");
        }
    }

    public void deleteCandidate(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            candidateRepository.delete(candidate.get());
        }else {
            throw new RuntimeException("Candidate not found");
        }
    }

    public CandidateDTO getOneCandidate(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            return mapperDTO.convertToDTO(candidate.get(), CandidateDTO.class);
        }else {
            throw new RuntimeException("Candidate not found");
        }
    }

    public List<CandidateDTO> getAllCandidates(int page, int limit) {
        if(page > 0) page--;
        List<Candidate> candidates = candidateRepository.findAll(PageRequest.of(page, limit)).getContent();
        return mapperDTO.convertListToListDto(candidates, CandidateDTO.class);
    }
}

