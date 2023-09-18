package com.tricBuzz.userService.squad.controller;

import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.squad.model.SquadGetModel;
import com.tricBuzz.userService.squad.model.SquadPostModel;
import com.tricBuzz.userService.squad.model.SquadSearchModel;
import com.tricBuzz.userService.squad.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SquadController {

    private final SquadService squadService;

    @GetMapping("squads/{id}")
    public SquadGetModel getSquad(@PathVariable Long id) {
        return squadService.getSquad(id);
    }

    @GetMapping("squads")
    public PaginationResponseModel<SquadGetModel> search(@RequestBody SquadSearchModel squadSearchModel) {
        return squadService.searchSquads(squadSearchModel);
    }

    @PostMapping("squads")
    public SquadGetModel create(@RequestBody SquadPostModel squadModel) {
        return squadService.createSquad(squadModel);
    }
}
