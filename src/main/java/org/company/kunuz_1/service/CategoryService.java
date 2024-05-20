package org.company.kunuz_1.service;

import org.company.kunuz_1.dto.CategoryCreateDTO;
import org.company.kunuz_1.dto.CategoryDTO;
import org.company.kunuz_1.dto.RegionDTO;
import org.company.kunuz_1.entity.CategoryEntity;
import org.company.kunuz_1.entity.RegionEntity;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.exception.AppBadException;
import org.company.kunuz_1.mapper.CategoryMapper;
import org.company.kunuz_1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryCreateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        categoryRepository.save(entity);

        return toDTO(entity);
    }

    private CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(Integer id, CategoryCreateDTO dto) {
        CategoryEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        categoryRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return true;
    }

    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new AppBadException("Region not found"));
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : iterable) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }


    public List<CategoryDTO> getAllByLang(LanguageEnum lang) {
        Iterable<CategoryMapper> iterable = categoryRepository.findAll(lang.name());
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryMapper entity : iterable) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CategoryDTO> getAllByLang2(LanguageEnum lang) {
        Iterable<CategoryEntity> iterable = categoryRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : iterable) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            switch (lang) {
                case EN ->  dto.setName(entity.getNameEn());
                case RU ->  dto.setName(entity.getNameRu());
                case UZ ->  dto.setName(entity.getNameUz());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
