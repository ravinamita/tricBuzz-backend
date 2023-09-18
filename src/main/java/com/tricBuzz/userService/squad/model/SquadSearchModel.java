package com.tricBuzz.userService.squad.model;

import com.tricBuzz.userService.common.model.PaginationSearchModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class SquadSearchModel extends PaginationSearchModel {

    @Nullable
    private Long organizationId;

    @Nullable
    private String name;
}
