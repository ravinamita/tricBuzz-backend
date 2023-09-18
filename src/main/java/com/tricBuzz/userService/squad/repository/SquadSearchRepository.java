package com.tricBuzz.userService.squad.repository;

import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.squad.entity.SquadEntity;
import com.tricBuzz.userService.squad.model.SquadSearchModel;

public interface SquadSearchRepository {

    PaginationResponseModel<SquadEntity> search(SquadSearchModel squadSearchModel);
}
