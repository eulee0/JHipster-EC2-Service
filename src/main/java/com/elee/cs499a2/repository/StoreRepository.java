package com.elee.cs499a2.repository;

import com.elee.cs499a2.domain.Store;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Store entity.
 */
@SuppressWarnings("unused")
public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query("select distinct store from Store store left join fetch store.books")
    List<Store> findAllWithEagerRelationships();

    @Query("select store from Store store left join fetch store.books where store.id =:id")
    Store findOneWithEagerRelationships(@Param("id") Long id);

}
