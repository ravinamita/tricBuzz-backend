package com.tricBuzz.userService.squad.model;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SquadPostModel {

    @Nullable
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long organizationId;

    @NonNull
    Set<Long> actors;
}
