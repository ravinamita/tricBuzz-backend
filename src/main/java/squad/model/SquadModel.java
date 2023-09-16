package squad.model;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SquadModel {

    @Nullable
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Long orgId;

    Set<ActorEntity> actors;
}
