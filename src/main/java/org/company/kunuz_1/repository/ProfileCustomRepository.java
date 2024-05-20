package org.company.kunuz_1.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.company.kunuz_1.dto.FilterResponseDTO;
import org.company.kunuz_1.dto.ProfileDTO;
import org.company.kunuz_1.dto.ProfileFilterDTO;
import org.company.kunuz_1.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileCustomRepository {

    @Autowired
    private EntityManager em;


    public FilterResponseDTO<ProfileEntity> filter(ProfileFilterDTO filter, int page, int size) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder query = new StringBuilder();
        if (filter.getName() != null) {
            query.append(" and c.name=:name ");
            params.put("name", filter.getName());
        }
        if (filter.getSurname() != null) {
            query.append(" and c.surname=:surname ");
            params.put("surname", filter.getSurname());
        }
        if (filter.getPhone() != null) {
            query.append(" and c.phone=:phone ");
            params.put("phone", filter.getPhone());
        }
        if (filter.getRole() != null) {
            query.append(" and c.role=:role ");
            params.put("role", filter.getRole());
        }
        if (filter.getCreatedDateTo() != null) {
            query.append(" and c.created_date >=: createdDateTo ");
            params.put("createdDateTo", filter.getCreatedDateTo());
        }
        if (filter.getCreatedDateFrom() != null) {
            query.append(" and c.created_date <=: createdDateFrom ");
            params.put("createdDateFrom", filter.getCreatedDateFrom());
        }

        StringBuilder selectSql = new StringBuilder("From ProfileEntity p where p.visible=true ");
        StringBuilder counttSdl = new StringBuilder("select count (p) From ProfileEntity p where p.visible=true ");

        Query selectQuery = em.createQuery(selectSql.toString());
        Query countQuery = em.createQuery(counttSdl.toString());

        for (Map.Entry<String, Object> entity : params.entrySet()) {
            selectQuery.setParameter(entity.getKey(), entity.getValue());
            countQuery.setParameter(entity.getKey(), entity.getValue());
        }

        selectQuery.setFirstResult(page * size);
        selectQuery.setMaxResults(size);
        List<ProfileEntity> list = selectQuery.getResultList();

        Long totalCount =(Long) countQuery.getSingleResult();

        return new FilterResponseDTO<ProfileEntity>(list,totalCount);
    }
}
