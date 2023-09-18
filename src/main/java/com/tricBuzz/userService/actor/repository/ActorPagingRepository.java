package com.tricBuzz.userService.actor.repository;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import com.tricBuzz.userService.actor.model.ActorSearchModel;
import com.tricBuzz.userService.common.model.PaginationResponseModel;

public interface ActorPagingRepository {
    PaginationResponseModel<ActorEntity> search(ActorSearchModel actorSearchModel);
}
