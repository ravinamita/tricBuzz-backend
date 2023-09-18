package com.tricBuzz.userService.actor.model;

import com.tricBuzz.userService.common.model.PaginationSearchModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class ActorSearchModel extends PaginationSearchModel {

    @Nullable
    private String name;
}
