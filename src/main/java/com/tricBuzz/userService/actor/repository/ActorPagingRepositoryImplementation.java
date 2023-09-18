package com.tricBuzz.userService.actor.repository;

import com.tricBuzz.userService.actor.entity.ActorEntity;
import com.tricBuzz.userService.actor.model.ActorSearchModel;
import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.common.repository.SearchAndPagingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorPagingRepositoryImplementation extends SearchAndPagingRepository<ActorEntity, ActorSearchModel> implements ActorPagingRepository{

    public ActorPagingRepositoryImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public PaginationResponseModel<ActorEntity> search(ActorSearchModel actorSearchModel) {
        return this.generatePaginatedResults(ActorEntity.class,actorSearchModel);
    }

    @Override
    protected List<Predicate> generatePredicateList(CriteriaBuilder criteriaBuilder, Root<ActorEntity> root, ActorSearchModel paginationSearchModel) {
        List<Predicate> predicates = new ArrayList<>();

        if ( paginationSearchModel.getName() != null)
            predicates.add(criteriaBuilder.equal(root.get("name"),paginationSearchModel.getName()));

        return predicates;
    }
}
