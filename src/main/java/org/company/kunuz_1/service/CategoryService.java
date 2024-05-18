package org.company.kunuz_1.service;

import org.company.kunuz_1.dto.CategoryDTO;
import org.company.kunuz_1.entity.CategoryEntity;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());

        categoryRepository.save(entity);

        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public Boolean update(Integer id, CategoryDTO dto) {
        CategoryEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        categoryRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
        /*
        studentRepository.deleteById(id);
         */
        CategoryEntity entity = get(id);
        categoryRepository.delete(entity);
        return true;
    }

    public CategoryEntity get(Integer id) {
        /*Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }
        return optional.get();*/
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Region not found");
        });
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : iterable) {
            CategoryDTO dto = new CategoryDTO();
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


    public List<CategoryDTO> getAllByLang(LanguageEnum lang) {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : iterable) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setLang(lang);
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
