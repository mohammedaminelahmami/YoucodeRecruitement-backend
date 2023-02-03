package com.example.youcodeRecruitment.Controller;

<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Service.HRService;
=======

import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.Service.HrService;
import com.example.youcodeRecruitment.Utils.PaginatedDto;
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
import com.example.youcodeRecruitment.dto.HRDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
@RequestMapping("/api/v1/")
public class HRController {
    private final HRService hrService;

    // method update HR
=======
@RequestMapping("/api/v1")
public class HrController {
    private final HrService hrService;
    private final CandidatService candidatService;

    @PostMapping("/hr")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHr(@Valid @RequestBody SaveHrRequest saveHrRequest) {
        hrService.createHr(saveHrRequest);
    }

    //   function update HR
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
    @PutMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid HrRequest hrRequest) {
        hrService.updateHr(hrRequest, Long.parseLong(id));
    }

<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
    // method delete Hr
=======
    //    function delete Hr
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
    @DeleteMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        hrService.deleteHr(Long.parseLong(id));
    }

<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
    // method get one Hr
=======
    //    function get one Hr
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
    @GetMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getOne(@PathVariable String id) {
        return hrService.getOneHr(Long.parseLong(id));
    }

<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
    // method get all HRS
=======
    //    function get all HRS
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
    @GetMapping("/hrs")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedDto<HRDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return hrService.getAllHrs(page, limit);
    }
<<<<<<< HEAD:src/main/java/com/example/youcodeRecruitment/Controller/HRController.java
}
=======

//    Method get all by First name
//    @GetMapping("/Hrs/search")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CandidateDTO> search(@RequestParam(value = "firstname", required = false) String firstName,
//                                  @RequestParam(value = "lastname", required = false) String lastName,
//                                  @RequestParam(value = "frontend_skills", required = false) String frontend_skills
//                           ) {
//        return candidatService.searchByFirstNameLastNameSkills(firstName, lastName, frontend_skills);
//    }
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


>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3:src/main/java/com/example/youcodeRecruitment/Controller/HrController.java
