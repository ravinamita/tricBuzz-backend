package com.tricBuzz.userService.squad.repository;

import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.common.repository.SearchAndPagingRepository;
import com.tricBuzz.userService.squad.entity.SquadEntity;
import com.tricBuzz.userService.squad.model.SquadSearchModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SquadSearchRepositoryImplementation extends SearchAndPagingRepository<SquadEntity,SquadSearchModel> implements SquadSearchRepository {

    public SquadSearchRepositoryImplementation(final EntityManager entityManager) {
        super(entityManager);
    }

    public List<Predicate> generatePredicateList(CriteriaBuilder criteriaBuilder,
                                                 Root<SquadEntity> root,SquadSearchModel paginationSearchModel) {

        List<Predicate> predicates = new ArrayList<>();

        if ( paginationSearchModel.getName() != null)
            predicates.add(criteriaBuilder.equal(root.get("name"),paginationSearchModel.getName()));

        if ( paginationSearchModel.getOrganizationId() != null)
            predicates.add(criteriaBuilder.equal(root.get("organization_id"),paginationSearchModel.getOrganizationId()));

        return predicates;
    }

    @Override
    public PaginationResponseModel<SquadEntity> search(SquadSearchModel paginationSearchModel) {
        return this.generatePaginatedResults(SquadEntity.class,paginationSearchModel);
    }
}
