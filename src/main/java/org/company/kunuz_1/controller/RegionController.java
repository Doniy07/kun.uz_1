package org.company.kunuz_1.controller;

import jakarta.validation.Valid;
import org.company.kunuz_1.dto.RegionCreateDTO;
import org.company.kunuz_1.dto.RegionDTO;
import org.company.kunuz_1.enums.LanguageEnum;
import org.company.kunuz_1.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<RegionDTO> addRegion(@Valid @RequestBody RegionCreateDTO region) {
        return ResponseEntity.ok().body(regionService.create(region));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                         @Valid @RequestBody RegionCreateDTO dto) {
        return ResponseEntity.ok().body(regionService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(regionService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> all() {
        return ResponseEntity.ok().body(regionService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") LanguageEnum lang) {
        return ResponseEntity.ok().body(regionService.getAllByLang(lang));
    }
}
