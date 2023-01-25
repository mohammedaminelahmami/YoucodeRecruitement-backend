package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.CandidatRequest;
import com.example.youcodeRecruitment.Service.CandidatService;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CandidatController {
    private final CandidatService candidatService;

//   function update candidate
    @PutMapping("/candidate/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid CandidatRequest candidatRequest) {
        candidatService.updateCandidat(candidatRequest, Long.parseLong(id));
    }

//    function delete candidate
    @DeleteMapping("/candidate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        candidatService.deleteCandidate(Long.parseLong(id));
    }

//    function get one candidate
    @GetMapping("/candidate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getOne(@PathVariable String id) {
        return candidatService.getOneCandidate(Long.parseLong(id));
    }

//    function get all candidates
    @GetMapping("/candidates")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return candidatService.getAllCandidates(page, limit);
    }
}
