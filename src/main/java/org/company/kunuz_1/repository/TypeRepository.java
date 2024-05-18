package org.company.kunuz_1.repository;

import org.company.kunuz_1.entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableArgumentResolver;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer>,
        PagingAndSortingRepository<TypeEntity, Integer> {
}
