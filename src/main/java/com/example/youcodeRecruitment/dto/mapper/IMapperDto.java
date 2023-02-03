package com.example.youcodeRecruitment.dto.mapper;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IMapperDto<D, E> {
    D convertToDTO(E entity, Class<D> dtoClass);
    E convertToEntity(D dto, Class<E> entityClass);
    List<D> convertListToListDto(List<E> listEntity, Class<D> dtoClass);
    List<E> convertListToListEntity(List<D> listDto, Class<E> entityClass);
    Page<D> convertPageToPageDto(Page<E> entityList, Class<D> outClass);
}