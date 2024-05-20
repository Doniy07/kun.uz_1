package org.company.kunuz_1.repository;

import org.company.kunuz_1.entity.RegionEntity;
import org.company.kunuz_1.mapper.RegionMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {

    List<RegionEntity> findAllByVisibleTrueOrderByOrderNumberDesc();

    @Query("from RegionEntity where visible = true order by orderNumber desc")
    List<RegionEntity> findAllVisible();

    @Query(value = " select id, " +
            " CASE :lang " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'EN' THEN name_en " +
            "   WHEN 'RU' THEN name_ru " +
            "  END as name " +
            "from regions order by order_number desc; ", nativeQuery = true)
    List<RegionMapper> findAll(@Param("lang") String lang);

}
