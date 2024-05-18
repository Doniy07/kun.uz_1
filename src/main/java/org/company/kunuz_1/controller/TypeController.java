package org.company.kunuz_1.controller;

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
    public ResponseEntity<TypeDTO> addType(@RequestBody TypeDTO typeDTO) {
        TypeDTO response = typeService.create(typeDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @RequestBody TypeDTO dto) {
        Boolean result = typeService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id) {
        Boolean result = typeService.delete(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<TypeDTO>> pageable(@RequestParam(value = "page", defaultValue = "1") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<TypeDTO> studentDTOList = typeService.pagination(page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }

    @GetMapping("/getAllByLang")
    public ResponseEntity<List<TypeDTO>> getAllByLang(@RequestParam("lang") LanguageEnum lang) {
        List<TypeDTO> typeDTOList =  typeService.getAllByLang(lang);
        return ResponseEntity.ok().body(typeDTOList);
    }
}
