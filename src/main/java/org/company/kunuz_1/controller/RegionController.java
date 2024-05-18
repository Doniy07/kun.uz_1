package org.company.kunuz_1.controller;

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
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO) {
        RegionDTO response = regionService.create(regionDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody RegionDTO dto) {
        Boolean result = regionService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        Boolean result = regionService.delete(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> all() {
        List<RegionDTO> regionDTOList =  regionService.getAll();
        return ResponseEntity.ok().body(regionDTOList);
    }

    @GetMapping("/getAllByLang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(@RequestParam("lang") LanguageEnum lang) {
        List<RegionDTO> regionDTOList =  regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(regionDTOList);
    }
}
