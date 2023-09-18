package com.tricBuzz.userService.common.repository;

import com.tricBuzz.userService.common.enums.OrderEnum;
import com.tricBuzz.userService.common.model.PaginationResponseModel;
import com.tricBuzz.userService.common.model.PaginationSearchModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public abstract class SearchAndPagingRepository <T,G extends PaginationSearchModel> {

    private final EntityManager entityManager;

    private List<Order> getOrderList(CriteriaBuilder criteriaBuilder, Root<T> root,G searchModel) {

        if (searchModel.getSortBy() == null) return new ArrayList<>();

        return searchModel.getSortBy().entrySet()
                .stream()
                .filter(e -> e.getKey() != null && e.getValue() != null)
                .map(
                        (e) -> e.getValue() == OrderEnum.ASC ?
                        criteriaBuilder.asc(root.get(e.getKey())) :
                        criteriaBuilder.desc(root.get(e.getKey())))
                .toList();
    }

    private long countQuery(CriteriaBuilder criteriaBuilder,
                               Class<T> tClass,G paginationSearchModel) {

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = countQuery.from(tClass);

        List<Predicate> predicates = generatePredicateList(criteriaBuilder,root,paginationSearchModel);
        countQuery.select(criteriaBuilder.count(root)).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private List<T> paginatedQuery(CriteriaBuilder criteriaBuilder, Class<T> tClass,G paginationSearchModel) {
        CriteriaQuery<T> paginatedQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = paginatedQuery.from(tClass);

        List<Predicate> predicates = generatePredicateList(criteriaBuilder,root,paginationSearchModel);
        List<Order> orders = getOrderList(criteriaBuilder,root,paginationSearchModel);

        paginatedQuery.select(root).where(predicates.toArray(new Predicate[0])).orderBy(orders);

        TypedQuery<T> typedQuery = entityManager.createQuery(paginatedQuery);
        typedQuery.setFirstResult((paginationSearchModel.getPage())*paginationSearchModel.getLimit());
        typedQuery.setMaxResults(paginationSearchModel.getLimit());
        return typedQuery.getResultList();
    }

    protected abstract List<Predicate> generatePredicateList(CriteriaBuilder criteriaBuilder, Root<T> root, G paginationSearchModel);

    public PaginationResponseModel<T> generatePaginatedResults(Class<T> tClass, G paginationSearchModel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        long countResults = countQuery(criteriaBuilder,tClass,paginationSearchModel);
        List<T> paginationResults = paginatedQuery(criteriaBuilder,tClass,paginationSearchModel);

        return PaginationResponseModel.<T>builder().
                pageCount(countResults)
                .pageNumber(paginationSearchModel.getPage())
                .pageSize(paginationSearchModel.getLimit())
                .entities(paginationResults).build();
    }
}
