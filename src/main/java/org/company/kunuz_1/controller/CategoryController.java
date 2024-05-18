package org.company.kunuz_1.controller;

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
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO response = categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody CategoryDTO dto) {
        Boolean result = categoryService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        Boolean result = categoryService.delete(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        List<CategoryDTO> regionDTOList = categoryService.getAll();
        return ResponseEntity.ok().body(regionDTOList);
    }

    @GetMapping("/getAllByLang")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(@RequestParam("lang") LanguageEnum lang) {
        List<CategoryDTO> regionDTOList = categoryService.getAllByLang(lang);
        return ResponseEntity.ok().body(regionDTOList);
    }
}
