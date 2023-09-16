package squad.entity;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class SquadEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private Long orgId;

    @OneToMany(mappedBy="id")
    Set<ActorEntity> actors;
}
