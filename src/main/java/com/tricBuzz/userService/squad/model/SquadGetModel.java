package com.tricBuzz.userService.squad.model;

import com.tricBuzz.userService.actor.model.ActorModel;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SquadGetModel {

    @Nullable
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long organizationId;

    @NonNull
    private Set<ActorModel> actors;
}
