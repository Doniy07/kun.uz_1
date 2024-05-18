package org.company.kunuz_1.service;

import org.company.kunuz_1.dto.RegionDTO;
import org.company.kunuz_1.entity.RegionEntity;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;


    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        regionRepository.save(entity);

        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        RegionEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
//        studentRepository.deleteById(id);
        RegionEntity entity = get(id);
        regionRepository.delete(entity);
        return true;
    }

    public RegionEntity get(Integer id) {
        /*Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        return optional.get();*/

        return regionRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Region not found");
        });
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setNameUz(entity.getNameUz());
            dto.setNameRu(entity.getNameRu());
            dto.setNameEn(entity.getNameEn());
            dto.setVisible(entity.getVisible());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }


    public List<RegionDTO> getAllByLang(LanguageEnum lang) {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setKey(lang);
            if (lang == LanguageEnum.UZ) {
                dto.setCurrentName(entity.getNameUz());
            }
            if (lang == LanguageEnum.RU) {
                dto.setCurrentName(entity.getNameRu());
            }
            if (lang == LanguageEnum.EN) {
                dto.setCurrentName(entity.getNameEn());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
