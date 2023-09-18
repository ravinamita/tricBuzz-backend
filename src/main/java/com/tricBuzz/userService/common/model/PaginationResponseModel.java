package com.tricBuzz.userService.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PaginationResponseModel<T> {

    long pageCount;
    int pageNumber;
    int pageSize;
    List<T> entities;
}
