package com.tricBuzz.userService.actor.repository;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<ActorEntity,Long> {
}