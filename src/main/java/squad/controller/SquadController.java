package squad.controller;
import com.tricBuzz.userService.actor.entity.ActorEntity;
import com.tricBuzz.userService.actor.model.ActorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import squad.model.SquadModel;

import java.util.Set;

public class SquadController {
    @GetMapping("squad/{id}")
    public SquadModel getSquad(@PathVariable Long id) {return new SquadModel();}

    @GetMapping("squad/{ogrId}")
    public SquadModel getSquad(@PathVariable Long orgId) {return new SquadModel();}

    @PostMapping("squad/")
    public SquadModel create(@RequestParam Set<ActorEntity> actors) {return new SquadModel();}

}
