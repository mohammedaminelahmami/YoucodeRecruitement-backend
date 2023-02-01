package com.example.youcodeRecruitment.Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class PaginatedDto<T> {
    private List<T> data;
    private int totalRecords;
    private int lastPage;
    private int pageNumber;

    public PaginatedDto(List<T> convertListToListDto, long totalElements, int lastPage, int number) {
        this.data = convertListToListDto;
        this.totalRecords = (int) totalElements;
        this.lastPage = lastPage;
        this.pageNumber = number + 1;
    }
}
