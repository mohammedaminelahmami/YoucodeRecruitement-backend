package com.example.youcodeRecruitment.dto.mapper;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
=======
>>>>>>> a463ba7e56bca5ae20e08420371ce7bc43429ed3

import java.util.List;

public interface IMapperDto<D, E> {
    D convertToDTO(E entity, Class<D> dtoClass);
    E convertToEntity(D dto, Class<E> entityClass);
    List<D> convertListToListDto(List<E> listEntity, Class<D> dtoClass);
    List<E> convertListToListEntity(List<D> listDto, Class<E> entityClass);
    Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass);
}