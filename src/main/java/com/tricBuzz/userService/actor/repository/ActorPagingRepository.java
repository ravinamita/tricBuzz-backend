package com.tricBuzz.userService.actor.repository;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorPagingRepository extends PagingAndSortingRepository<ActorEntity, Long> {
}
