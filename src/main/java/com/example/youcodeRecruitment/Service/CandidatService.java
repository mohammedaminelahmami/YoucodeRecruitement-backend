package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Request.CandidatRequest;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CandidatService {
    private final CandidateRepository candidateRepository;
    private final IMapperDto<CandidateDTO, Candidate> mapperDTO;

    public void updateCandidat(CandidatRequest candidatRequest, Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            candidate.get().setFirstname(candidatRequest.getFirstName());
            candidate.get().setLastname(candidatRequest.getLastName());
            candidate.get().setEmail(candidatRequest.getEmail());
            candidate.get().setPassword(candidatRequest.getPassword());
            candidate.get().setImage(candidatRequest.getImage());
            candidateRepository.save(candidate.get());
        }else{
            throw new RuntimeException("Candidat not found");
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

