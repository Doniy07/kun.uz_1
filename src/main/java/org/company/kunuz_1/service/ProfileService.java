package org.company.kunuz_1.service;

import org.company.kunuz_1.dto.*;
import org.company.kunuz_1.entity.CategoryEntity;
import org.company.kunuz_1.entity.ProfileEntity;
import org.company.kunuz_1.exception.AppBadException;
import org.company.kunuz_1.repository.ProfileCustomRepository;
import org.company.kunuz_1.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileCustomRepository profileCustomRepository;

    public ProfileDTO create(ProfileCreateDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());

        profileRepository.save(entity);

        return toDTO(entity);
    }

    private ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(ProfileCreateDTO dto, Integer id) {
        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        profileRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new AppBadException("Profile not found"));
    }

    public List<ProfileDTO> getAll() {
        Iterable<ProfileEntity> iterable = profileRepository.findAll();
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : iterable) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }

    public PageImpl<ProfileDTO> filter(ProfileFilterDTO filter, int page, int size) {
        FilterResponseDTO<ProfileEntity> filterResponse = profileCustomRepository.filter(filter, page, size);


        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : filterResponse.getContent()) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<ProfileDTO>(dtoList, PageRequest.of(page,size), filterResponse.getTotalCount());
    }
}
