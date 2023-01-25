package com.example.youcodeRecruitment.Controller;


import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Service.HrService;
import com.example.youcodeRecruitment.dto.HRDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class HrController {
    private final HrService hrService;

    //   function update HR
    @PutMapping("/Hr/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid HrRequest hrRequest) {
        hrService.updateHr(hrRequest, Long.parseLong(id));
    }

    //    function delete Hr
    @DeleteMapping("/Hr/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        hrService.deleteHr(Long.parseLong(id));
    }

    //    function get one Hr
    @GetMapping("/Hr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HRDTO getOne(@PathVariable String id) {
        return hrService.getOneHr(Long.parseLong(id));
    }

    //    function get all HRS
    @GetMapping("/Hrs")
    @ResponseStatus(HttpStatus.OK)
    public List<HRDTO> getAllById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return hrService.getAllHrs(page, limit);
    }
}
