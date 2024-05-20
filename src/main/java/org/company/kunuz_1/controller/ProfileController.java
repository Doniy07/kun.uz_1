package org.company.kunuz_1.controller;

import jakarta.validation.Valid;
import org.company.kunuz_1.dto.ProfileCreateDTO;
import org.company.kunuz_1.dto.ProfileDTO;
import org.company.kunuz_1.dto.ProfileFilterDTO;
import org.company.kunuz_1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> addProfile(@Valid @RequestBody ProfileCreateDTO profile) {
        return ResponseEntity.ok().body(profileService.create(profile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateProfile(@PathVariable Integer id,
                                                 @Valid @RequestBody ProfileCreateDTO dto) {
        return ResponseEntity.ok().body(profileService.update(dto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProfile(@PathVariable Integer id) {
        return ResponseEntity.ok().body(profileService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> all() {
        return ResponseEntity.ok().body(profileService.getAll());
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                                    @RequestBody ProfileFilterDTO filter) {
        PageImpl<ProfileDTO> profileDTOPage = profileService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(profileDTOPage);
    }
}
