package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.HRRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.Service.CandidateService;
import com.example.youcodeRecruitment.Service.HRService;
import com.example.youcodeRecruitment.Utils.PaginatedDto;
import com.example.youcodeRecruitment.dto.CandidateDTO;
import com.example.youcodeRecruitment.dto.HRDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HRController {
    private final HRService hrService;
    private final CandidateService candidateService;

    @PostMapping("/hr")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHr(@Valid @RequestBody SaveHrRequest saveHrRequest) {
        hrService.createHr(saveHrRequest);
    }

    //   function update HR
    @PutMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid HRRequest hrRequest) {
        hrService.updateHr(hrRequest, Long.parseLong(id));
    }

    //    function delete Hr
    @DeleteMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        hrService.deleteHr(Long.parseLong(id));
    }

    //    function get one Hr
    @GetMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getOne(@PathVariable String id) {
        return hrService.getOneHr(Long.parseLong(id));
    }

    //    function get all HRS
    @GetMapping("/hrs")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedDto<HRDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return hrService.getAllHrs(page, limit);
    }


//    Method get all by First name
//    @GetMapping("/Hrs/search")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CandidateDTO> search(@RequestParam(value = "firstname", required = false) String firstName,
//                                  @RequestParam(value = "lastname", required = false) String lastName,
//                                  @RequestParam(value = "frontend_skills", required = false) String frontend_skills
//                           ) {
//        return candidatService.searchByFirstNameLastNameSkills(firstName, lastName, frontend_skills);
//    }

    //    Method search
    @GetMapping("/Hrs/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateDTO> search(@RequestParam(value = "firstname", required = false) String firstname,
                                     @RequestParam(value = "lastname", required = false) String lastname,
                                     @RequestParam(value = "frontend", required = false) String frontend,
                                     @RequestParam(value = "backend", required = false) String backend,
                                     @RequestParam(value = "db", required = false) String db,
                                     @RequestParam(value = "outil", required = false) String outil) {
        return candidateService.searchByFirstNameLastNameSkills(firstname, lastname, frontend, backend, db, outil);
    }
}