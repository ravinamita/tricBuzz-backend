package com.tricBuzz.userService.actor.service;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import com.tricBuzz.userService.actor.model.ActorModel;
import com.tricBuzz.userService.actor.repository.ActorPagingRepository;
import com.tricBuzz.userService.actor.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorPagingRepository actorPagingRepository;

    public ActorModel getActor(long id) {

        Optional<ActorEntity> optionalActorEntity = actorRepository.findById(id);

        if (optionalActorEntity.isEmpty()) {
            throw new RuntimeException(" Entity for id: " +id + "not found");
        }

        return convertActorEntity(optionalActorEntity.get());
    }

    public List<ActorModel> getActors(int pageNumber, int limit) {
        Page<ActorEntity> page = actorPagingRepository.findAll(PageRequest.of(pageNumber,limit));
        return page.map(this::convertActorEntity).getContent();
    }

    public ActorModel createActor(ActorModel actorModel) {
        ActorEntity entity = convertActorModel(actorModel);
        return convertActorEntity(actorRepository.save(entity));
    }

    private ActorEntity convertActorModel(ActorModel model) {
        return new ModelMapper().map(model,ActorEntity.class);
    }

    private ActorModel convertActorEntity(ActorEntity entity) {
        ActorModel model = new ModelMapper().map(entity,ActorModel.class);
        int currentYear = Year.now().getValue();

        model.setAge(currentYear - model.getBirthYear());
        return model;
    }
}
