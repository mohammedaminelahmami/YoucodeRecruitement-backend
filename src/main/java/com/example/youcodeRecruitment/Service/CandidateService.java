package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Utils.PaginatedDto;
import com.example.youcodeRecruitment.Request.CandidateRequest;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public PaginatedDto<CandidateDTO> getAllCandidate(int page, int limit) {
        if (page > 0) page--;
        Page<Candidate> pageCandidate = candidateRepository.findAll(PageRequest.of(page, limit));
        List<CandidateDTO> candidatedtos = mapperDTO.convertListToListDto(pageCandidate.getContent(), CandidateDTO.class);
        return new PaginatedDto<>(candidatedtos, pageCandidate.getTotalElements(), pageCandidate.getTotalPages(), pageCandidate.getNumber());

    }

    public List<CandidateDTO> searchByFirstNameLastNameSkills(String firstname, String lastname,String frontend, String backend, String db, String outil) {

        List<Candidate> candidates = candidateRepository.findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContainingOrSkillsFrontendIgnoreCaseContainingOrSkillsBackendIgnoreCaseContainingOrSkillsDbIgnoreCaseContainingOrSkillsOutilIgnoreCaseContaining(firstname,lastname,frontend,backend,db,outil);
        List<CandidateDTO> candidateDTOS = mapperDTO.convertListToListDto(candidates,CandidateDTO.class);
        return candidateDTOS;
    }

    
}

