package org.company.kunuz_1.service;


import org.company.kunuz_1.dto.TypeCreateDTO;
import org.company.kunuz_1.dto.TypeDTO;
import org.company.kunuz_1.entity.TypeEntity;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.exception.AppBadException;
import org.company.kunuz_1.mapper.TypeMapper;
import org.company.kunuz_1.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public TypeDTO create(TypeCreateDTO dto) {
        TypeEntity entity = new TypeEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typeRepository.save(entity);


        return toDTO(entity);
    }

    private TypeDTO toDTO(TypeEntity entity) {
        TypeDTO dto = new TypeDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(Integer id, TypeCreateDTO dto) {
        TypeEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typeRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
        typeRepository.deleteById(id);
        /*TypeEntity entity = get(id);
        typeRepository.delete(entity);*/
        return true;
    }

    public TypeEntity get(Integer id) {
        return typeRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Type not found");
        });
    }

    public List<TypeDTO> getAllByLang2(LanguageEnum lang) {
        Iterable<TypeEntity> iterable = typeRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<TypeDTO> dtoList = new LinkedList<>();
        for (TypeEntity entity : iterable) {
            TypeDTO dto = new TypeDTO();
            dto.setId(entity.getId());
            switch (lang) {
                case EN ->  dto.setName(entity.getNameEn());
                case RU ->  dto.setName(entity.getNameRu());
                case UZ ->  dto.setName(entity.getNameUz());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<TypeDTO> getAllByLang(LanguageEnum lang) {
        Iterable<TypeMapper> iterable = typeRepository.findAll(lang.name());
        List<TypeDTO> dtoList = new LinkedList<>();
        for (TypeMapper entity : iterable) {
            TypeDTO dto = new TypeDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public PageImpl<TypeDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TypeEntity> pageObj = typeRepository.findAll(pageable);

        List<TypeDTO> dtoList = new LinkedList<>();
        for (TypeEntity entity : pageObj.getContent()) {
            TypeDTO dto = new TypeDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setNameUz(entity.getNameUz());
            dto.setNameRu(entity.getNameRu());
            dto.setNameEn(entity.getNameEn());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }

        long totalCount = pageObj.getTotalElements();

        return new PageImpl<TypeDTO>(dtoList, pageable, totalCount);
    }
}
