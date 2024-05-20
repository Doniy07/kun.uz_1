package org.company.kunuz_1.controller;

import jakarta.validation.Valid;
import org.company.kunuz_1.dto.TypeCreateDTO;
import org.company.kunuz_1.dto.TypeDTO;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("/create")
    public ResponseEntity<TypeDTO> addType(@Valid @RequestBody TypeCreateDTO typeDTO) {
        return ResponseEntity.ok().body(typeService.create(typeDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateType(@PathVariable("id") Integer id,
                                               @Valid @RequestBody TypeCreateDTO dto) {
        return ResponseEntity.ok().body(typeService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteType(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(typeService.delete(id));
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<TypeDTO>> pageable(@RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<TypeDTO> studentDTOList = typeService.pagination(page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/lang")
    public ResponseEntity<List<TypeDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") LanguageEnum lang) {
        return ResponseEntity.ok().body(typeService.getAllByLang(lang));
    }
}
