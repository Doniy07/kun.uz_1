package org.company.kunuz_1.service;

import org.company.kunuz_1.dto.RegionCreateDTO;
import org.company.kunuz_1.dto.RegionDTO;
import org.company.kunuz_1.entity.RegionEntity;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.exception.AppBadException;
import org.company.kunuz_1.mapper.RegionMapper;
import org.company.kunuz_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;


    public RegionDTO create(RegionCreateDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);

        return toDTO(entity);
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(Integer id, RegionCreateDTO dto) {
        RegionEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
        regionRepository.deleteById(id);
        /*RegionEntity entity = get(id);
        regionRepository.delete(entity);*/
        return true;
    }

    public RegionEntity get(Integer id) {
        /*Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        return optional.get();*/
        return regionRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Category not found");
        });
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }


    public List<RegionDTO> getAllByLang2(LanguageEnum lang) {
        Iterable<RegionEntity> iterable = regionRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            RegionDTO dto = new RegionDTO();
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

    public List<RegionDTO> getAllByLang(LanguageEnum lang) {
        Iterable<RegionMapper> iterable = regionRepository.findAll(lang.name());
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionMapper entity : iterable) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
