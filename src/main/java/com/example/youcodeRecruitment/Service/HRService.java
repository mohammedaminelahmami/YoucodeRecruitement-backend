package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Repository.HRRepository;
import com.example.youcodeRecruitment.Request.HRRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.Utils.PaginatedDto;
import com.example.youcodeRecruitment.dto.HRDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class HRService {
    private final HRRepository hrRepository;
    private final IMapperDto<HRDTO, HR> mapperDTO;
    private final IMapperDto<SaveHrRequest, HR> mapperSaveRequest;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createHr(SaveHrRequest saveHrRequest) {
        if (hrRepository.findByEmail(saveHrRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        HR hr = mapperSaveRequest.convertToEntity(saveHrRequest, HR.class);
        if (hr != null) {
            hr.setPassword(bCryptPasswordEncoder.encode(saveHrRequest.getPassword()));
            hrRepository.save(hr);
        } else {
            throw new RuntimeException("Hr is null");
        }
    }

    public void updateHr(HRRequest hrRequest, Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if (hr.isPresent()) {
            hr.get().setFirstname(hrRequest.getFirstName());
            hr.get().setLastname(hrRequest.getLastName());
            hr.get().setEmail(hrRequest.getEmail());
            hr.get().setPassword(hrRequest.getPassword());
            hr.get().setImage(hrRequest.getImage());
            hrRepository.save(hr.get());
        } else {
            throw new RuntimeException("Hr not found");
        }
    }

    public void deleteHr(Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if (hr.isPresent()) {
            hrRepository.delete(hr.get());
        } else {
            throw new RuntimeException("Hr not found");
        }
    }

    public HRDTO getOneHr(Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if (hr.isPresent()) {
            return mapperDTO.convertToDTO(hr.get(), HRDTO.class);
        } else {
            throw new RuntimeException("Hr not found");
        }
    }

    public PaginatedDto<HRDTO> getAllHrs(int page, int limit) {
        if (page > 0) page--;
        Page<HR> pageHrs = hrRepository.findAll(PageRequest.of(page, limit));
        List<HRDTO> hrdtos =  mapperDTO.convertListToListDto(pageHrs.getContent(), HRDTO.class);
        return new PaginatedDto<>(hrdtos, pageHrs.getTotalElements(), pageHrs.getTotalPages(), pageHrs.getNumber());

    }
}




