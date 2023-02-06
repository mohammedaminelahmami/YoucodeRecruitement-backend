package com.example.youcodeRecruitment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperDtoImpl<D, E> implements IMapperDto<D, E> {

    @Autowired
    ModelMapper mapper;

    @Override
    public D convertToDTO(E entity, Class<D> dtoClass) {
        if(entity == null) {
            return null;
        }
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return mapper.map(entity, dtoClass);
    }

    @Override
    public E convertToEntity(D dto, Class<E> entityClass) {
        if(dto == null) {
            return null;
        }
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return mapper.map(dto, entityClass);
    }

    @Override
    public List<D> convertListToListDto(List<E> listEntity, Class<D> dtoClass) {
        if(listEntity == null) {
            return Collections.emptyList();
        }
        return listEntity.stream().map((entity) -> convertToDTO(entity, dtoClass)).toList(); // immutable
        // return listEntity.stream().map((entity) -> convertToDTO(entity, dtoClass)).collect(Collectors.toList()); // mutable
    }

    @Override
    public List<E> convertListToListEntity(List<D> listDto, Class<E> entityClass) {
        if(listDto == null) {
            return Collections.emptyList();
        }
        return listDto.stream().map(dto -> convertToEntity(dto, entityClass)).collect(Collectors.toList());
    }

    @Override
    public Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass) {
        if(entityList == null)
            return Page.empty();

        List<D> all =  entityList.stream().map(entity -> convertToDTO(entity,outClass)).collect(Collectors.toList());
        return new PageImpl<>(all);
    }
}