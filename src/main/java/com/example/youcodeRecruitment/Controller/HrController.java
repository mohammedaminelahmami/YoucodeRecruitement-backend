package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.HRRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.Service.AuthService;
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
public class HrController {
    private final HRService hrService;
    private final CandidateService candidatService;
    private final AuthService authService;

    // function get hr profile
    @GetMapping("/hr/profile")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getHrProfile() {
        return authService.getProfileHR();
    }
    @PostMapping("/hr")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHr(@Valid @RequestBody SaveHrRequest saveHrRequest) {
        hrService.createHr(saveHrRequest);
    }

    //   function update HR
    @PutMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id, @RequestBody @Valid HRRequest hrRequest) {
        hrService.updateHr(hrRequest, Long.parseLong(id));
    }

    @DeleteMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        hrService.deleteHr(Long.parseLong(id));
    }

    @GetMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getOne(@PathVariable String id) {
        return hrService.getOneHr(Long.parseLong(id));
    }

    @GetMapping("/hrs")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedDto<HRDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return hrService.getAllHrs(page, limit);
    }

    @GetMapping("/Hrs/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidateDTO> search(@RequestParam(value = "firstname", required = false) String firstname,
                                     @RequestParam(value = "lastname", required = false) String lastname,
                                     @RequestParam(value = "frontend", required = false) String frontend,
                                     @RequestParam(value = "backend", required = false) String backend,
                                     @RequestParam(value = "db", required = false) String db,
                                     @RequestParam(value = "outil", required = false) String outil) {
        return candidatService.searchByFirstNameLastNameSkills(firstname, lastname, frontend, backend, db, outil);
    }
}
