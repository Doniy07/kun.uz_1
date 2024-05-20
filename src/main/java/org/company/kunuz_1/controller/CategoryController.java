package org.company.kunuz_1.controller;

import jakarta.validation.Valid;
import org.company.kunuz_1.dto.CategoryCreateDTO;
import org.company.kunuz_1.dto.CategoryDTO;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryCreateDTO categoryDTO) {
        return ResponseEntity.ok().body(categoryService.create(categoryDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateCategory(@PathVariable("id") Integer id,
                                          @Valid @RequestBody CategoryCreateDTO dto) {
        return ResponseEntity.ok().body(categoryService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(categoryService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") LanguageEnum lang) {
        return ResponseEntity.ok().body(categoryService.getAllByLang(lang));
    }
}
