package com.tricBuzz.userService.actor.controller;

import com.tricBuzz.userService.actor.model.ActorModel;
import com.tricBuzz.userService.actor.model.ActorSearchModel;
import com.tricBuzz.userService.actor.service.ActorService;
import com.tricBuzz.userService.common.model.PaginationResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("actors")
    public PaginationResponseModel<ActorModel> search(@RequestBody ActorSearchModel actorSearchModel) {
        return actorService.getActors(actorSearchModel);
    }

    @GetMapping("actors/{id}")
    public ActorModel get(@PathVariable Long id) {
        return actorService.getActorModel(id);
    }

    @PostMapping("actors")
    public ActorModel create(@RequestBody ActorModel model) {
        return actorService.createActor(model);
    }
}
