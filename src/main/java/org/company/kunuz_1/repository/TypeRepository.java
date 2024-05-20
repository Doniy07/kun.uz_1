package org.company.kunuz_1.repository;

import org.company.kunuz_1.entity.TypeEntity;
import org.company.kunuz_1.mapper.TypeMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;

import java.util.List;

public interface TypeRepository extends CrudRepository<TypeEntity, Integer>,
        PagingAndSortingRepository<TypeEntity, Integer> {

    List<TypeEntity> findAllByVisibleTrueOrderByOrderNumberDesc();

    @Query(value = "select id," +
            "CASE :lang" +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'UZ' THEN name_uz " +
            "from types order by order_number desc; ", nativeQuery = true)
    List<TypeMapper> findAll(@Param("lang") String lang);
}
