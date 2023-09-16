package com.tricBuzz.userService.actor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class ActorEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private int birthYear;
    private int yearsPlayed;

    @ManyToMany
    Set<ActorEntity> actors;
}
