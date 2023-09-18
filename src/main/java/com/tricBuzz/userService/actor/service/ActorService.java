package com.tricBuzz.userService.actor.service;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import com.tricBuzz.userService.actor.model.ActorModel;
import com.tricBuzz.userService.actor.model.ActorSearchModel;
import com.tricBuzz.userService.actor.repository.ActorPagingRepository;
import com.tricBuzz.userService.actor.repository.ActorRepository;
import com.tricBuzz.userService.common.exception.NotFoundError;
import com.tricBuzz.userService.common.model.PaginationResponseModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorPagingRepository actorPagingRepository;

    public ActorModel getActorModel(long id) {
        return convertActorEntity(getActor(id));
    }

    public ActorEntity getActor(long id) {
        Optional<ActorEntity> optionalActorEntity = actorRepository.findById(id);

        if (optionalActorEntity.isEmpty()) {
            throw new NotFoundError("Actor for  id: " + id + " not found");
        }

        return optionalActorEntity.get();
    }

    public PaginationResponseModel<ActorModel> getActors(ActorSearchModel actorSearchModel) {
        PaginationResponseModel<ActorEntity> actorEntityPaginationResponseModel = actorPagingRepository.search(actorSearchModel);
        return PaginationResponseModel.<ActorModel>builder()
                .pageSize(actorEntityPaginationResponseModel.getPageSize())
                .pageCount(actorEntityPaginationResponseModel.getPageCount())
                .pageNumber(actorEntityPaginationResponseModel.getPageNumber())
                .entities(
                        actorEntityPaginationResponseModel.getEntities()
                                .stream()
                                .map(this::convertActorEntity)
                                .toList())
                .build();
    }

    public ActorModel createActor(ActorModel actorModel) {
        ActorEntity entity = convertActorModel(actorModel);
        return convertActorEntity(actorRepository.save(entity));
    }

    private ActorEntity convertActorModel(ActorModel model) {
        return new ModelMapper().map(model, ActorEntity.class);
    }

    private ActorModel convertActorEntity(ActorEntity entity) {
        ActorModel model = new ModelMapper().map(entity,ActorModel.class);
        int currentYear = Year.now().getValue();

        model.setAge(currentYear - model.getBirthYear());
        return model;
    }
}
