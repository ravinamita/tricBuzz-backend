package com.tricBuzz.userService.squad.service;

import com.tricBuzz.userService.actor.service.ActorService;
import com.tricBuzz.userService.common.exception.NotFoundError;
import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.squad.entity.SquadEntity;
import com.tricBuzz.userService.squad.model.SquadGetModel;
import com.tricBuzz.userService.squad.model.SquadPostModel;
import com.tricBuzz.userService.squad.model.SquadSearchModel;
import com.tricBuzz.userService.squad.repository.SquadRepository;
import com.tricBuzz.userService.squad.repository.SquadSearchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SquadService {

    private final ModelMapper modelMapper;
    private final SquadRepository squadRepository;
    private final ActorService actorService;
    private final SquadSearchRepository squadSearchRepository;

    public SquadGetModel createSquad(SquadPostModel squadModel) {
        SquadEntity entity = modelMapper.map(squadModel,SquadEntity.class);

        entity.setActors(squadModel.getActors().stream().map(
                actorService::getActor
        ).collect(Collectors.toSet()));
        return convertSquadEntity(squadRepository.save(entity));
    }

    public SquadGetModel getSquad(long id) {
        Optional<SquadEntity> optionalSquadEntity = squadRepository.findById(id);

        if (optionalSquadEntity.isEmpty())
            throw new NotFoundError("Cannot find squad with id " + id);

        return convertSquadEntity(optionalSquadEntity.get());
    }

    public PaginationResponseModel<SquadGetModel> searchSquads(SquadSearchModel squadSearchModel) {
        PaginationResponseModel<SquadEntity> squadEntityPaginationResponseModel = squadSearchRepository.search(squadSearchModel);

        System.out.println(squadEntityPaginationResponseModel.getEntities());

        return PaginationResponseModel.<SquadGetModel>builder()
                .pageSize(squadEntityPaginationResponseModel.getPageSize())
                .pageCount(squadEntityPaginationResponseModel.getPageCount())
                .pageNumber(squadEntityPaginationResponseModel.getPageNumber())
                .entities(
                        squadEntityPaginationResponseModel.getEntities()
                                .stream()
                                .map(this::convertSquadEntity)
                                .toList())
                .build();
    }

    private SquadGetModel convertSquadEntity(SquadEntity squadEntity) {
        return modelMapper.map(squadEntity, SquadGetModel.class);
    }

    private SquadEntity convertSquadModel(SquadPostModel squadModel) {
        return modelMapper.map(squadModel, SquadEntity.class);
    }
}
