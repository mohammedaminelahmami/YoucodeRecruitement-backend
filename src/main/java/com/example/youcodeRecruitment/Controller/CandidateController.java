package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.CandidateRequest;
import com.example.youcodeRecruitment.Service.CandidateService;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CandidateController {
    private final CandidateService candidateService;

    // method update candidate
    @PutMapping("/candidate/{id}") // idCandidate
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id, @RequestBody CandidateRequest candidateRequest) {
        candidateService.updateCandidate(candidateRequest, Long.parseLong(id));
    }

    @PutMapping("/candidateImage/{id}") // idCandidate
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateImage(@PathVariable String id, @RequestParam("imageFile") MultipartFile imageFile) {
        candidateService.updateCandidateImage(Long.parseLong(id), imageFile);
    }

    // method delete candidate
    @DeleteMapping("/candidate/{id}") // idCandidate
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        candidateService.deleteCandidate(Long.parseLong(id));
    }

    // method get one candidate
    @GetMapping("/candidate/{id}") // idCandidate
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getOne(@PathVariable String id) {
        return candidateService.getOneCandidate(Long.parseLong(id));
    }

    // method get all candidates
    @GetMapping("/candidates")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return candidateService.getAllCandidates(page, limit);
    }
}
