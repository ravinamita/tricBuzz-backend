package com.tricBuzz.userService.common.model;

import com.tricBuzz.userService.common.enums.OrderEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

@Getter
@Setter
public class PaginationSearchModel {

    @NonNull
    private int page;

    @NonNull
    private int limit;

    @Nullable
    private Map<String, OrderEnum> sortBy;
}
