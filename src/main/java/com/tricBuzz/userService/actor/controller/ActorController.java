package com.tricBuzz.userService.actor.controller;

import com.tricBuzz.userService.actor.model.ActorModel;
import com.tricBuzz.userService.actor.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("actors")
    public List<ActorModel> get(@RequestParam("page") Integer page,
                                @RequestParam("limit") Integer limit) {
        return actorService.getActors(page,limit);
    }

    @GetMapping("actors/{id}")
    public ActorModel get(@PathVariable Long id) {
        return actorService.getActor(id);
    }

    @PostMapping("actors/")
    public ActorModel create(@RequestBody ActorModel model) {
        return actorService.createActor(model);
    }
}
