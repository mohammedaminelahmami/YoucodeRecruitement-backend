package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Skills;
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


//    public List<CandidateDTO> searchByFirstNameLastName(String firstName, String lastName, String frontend_skills, String backend_skills, String db_skills, String outil_skills) {
//       List<Candidate> candidates = candidateRepository.searchCandidateByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrSkills_Backend_skills(firstName, lastName, backend_skills);
//       List<CandidateDTO> candidateDTOS = mapperDTO.convertListToListDto(candidates,CandidateDTO.class);
//       return candidateDTOS;
//    }

    public List<CandidateDTO> searchByFirstNameLastNameSkills(String firstname, String lastname,String frontend, String backend, String db, String outil) {

        List<Candidate> candidates = candidateRepository.findByFirstnameIgnoreCaseContainingOrLastnameIgnoreCaseContainingOrSkillsFrontendIgnoreCaseContainingOrSkillsBackendIgnoreCaseContainingOrSkillsDbIgnoreCaseContainingOrSkillsOutilIgnoreCaseContaining(firstname,lastname,frontend,backend,db,outil);
        List<CandidateDTO> candidateDTOS = mapperDTO.convertListToListDto(candidates,CandidateDTO.class);
        return candidateDTOS;
    }
}

