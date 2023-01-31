package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Repository.HrRepository;
import com.example.youcodeRecruitment.Request.HrRequest;
import com.example.youcodeRecruitment.Request.SaveHrRequest;
import com.example.youcodeRecruitment.dto.HRDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HrService {
    private final HrRepository hrRepository;
    private final IMapperDto<HRDTO, HR> mapperDTO;
    private final IMapperDto<SaveHrRequest, HR> mapperSaveRequest;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public  void createHr(SaveHrRequest saveHrRequest) {
        if(hrRepository.findByEmail(saveHrRequest.getEmail()).isPresent()){
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
    public void updateHr(HrRequest hrRequest, Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if(hr.isPresent()) {
            hr.get().setFirstname(hrRequest.getFirstName());
            hr.get().setLastname(hrRequest.getLastName());
            hr.get().setEmail(hrRequest.getEmail());
            hr.get().setPassword(hrRequest.getPassword());
            hr.get().setImage(hrRequest.getImage());
            hrRepository.save(hr.get());
        }else{
            throw new RuntimeException("Hr not found");
        }
    }

    public void deleteHr(Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if(hr.isPresent()) {
            hrRepository.delete(hr.get());
        }else {
            throw new RuntimeException("Hr not found");
        }
    }

    public HRDTO getOneHr(Long id) {
        Optional<HR> hr = hrRepository.findById(id);
        if(hr.isPresent()) {
            return mapperDTO.convertToDTO(hr.get(), HRDTO.class);
        }else {
            throw new RuntimeException("Hr not found");
        }
    }

    public List<HRDTO> getAllHrs(int page, int limit) {
        if(page > 0) page--;
            List<HR> hrs = hrRepository.findAll(PageRequest.of(page, limit)).getContent();
            return mapperDTO.convertListToListDto(hrs, HRDTO.class);

    }
}

