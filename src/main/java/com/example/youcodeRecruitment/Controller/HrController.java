package com.example.youcodeRecruitment.Controller;


import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.Service.HrService;
import com.example.youcodeRecruitment.Utils.PaginatedDto;
import com.example.youcodeRecruitment.dto.HRDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HrController {
    private final HrService hrService;

    @PostMapping("/hr")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHr(@Valid @RequestBody SaveHrRequest saveHrRequest) {
        hrService.createHr(saveHrRequest);
    }

    //   function update HR
    @PutMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid HrRequest hrRequest) {
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
}
