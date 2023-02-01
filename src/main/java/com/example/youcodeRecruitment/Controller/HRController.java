package com.example.youcodeRecruitment.Controller;


import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Service.HRService;
import com.example.youcodeRecruitment.dto.HRDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class HRController {
    private final HRService hrService;

    // method update HR
    @PutMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid HrRequest hrRequest) {
        hrService.updateHr(hrRequest, Long.parseLong(id));
    }

    // method delete Hr
    @DeleteMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        hrService.deleteHr(Long.parseLong(id));
    }

    // method get one Hr
    @GetMapping("/hr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getOne(@PathVariable String id) {
        return hrService.getOneHr(Long.parseLong(id));
    }

    // method get all HRS
    @GetMapping("/hrs")
    @ResponseStatus(HttpStatus.OK)
    public List<HRDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return hrService.getAllHrs(page, limit);
    }
}