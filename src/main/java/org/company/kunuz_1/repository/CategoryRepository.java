package org.company.kunuz_1.repository;

import org.company.kunuz_1.entity.CategoryEntity;
import org.company.kunuz_1.mapper.CategoryMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    @Query(value = " select id, order_number, " +
            " CASE :lang " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'EN' THEN name_en " +
            "   WHEN 'RU' THEN name_ru " +
            "  END as name " +
            "from categories order by order_number desc; ", nativeQuery = true)
    List<CategoryMapper> findAll(@Param("lang") String lang);

    List<CategoryEntity> findAllByVisibleTrueOrderByOrderNumberDesc();
}
