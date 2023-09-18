package com.tricBuzz.userService.actor.model;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ActorModel {

    @Nullable
    private Long id;

    @NonNull
    private String name;

    @Nullable
    private int age;

    @NonNull
    private Integer birthYear;

    @NonNull
    private Integer yearsPlayed;
}
