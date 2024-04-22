package com.rockbitegames.sandship.service;

import com.rockbitegames.sandship.dto.MaterialDto;
import com.rockbitegames.sandship.mapper.MaterialMapper;
import com.rockbitegames.sandship.repository.MaterialRepository;
import com.rockbitegames.sandship.validation.MaterialValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialService {

    private MaterialRepository materialRepository;
    private MaterialValidator materialValidator;
    private MaterialMapper mapper;


    public MaterialDto createNew(MaterialDto materialDto) {
        materialValidator.validate(materialDto);
        return mapper.toDto(materialRepository.save(mapper.toEntity(materialDto)));
    }


    public List<MaterialDto> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialType -> mapper.toDto(materialType))
                .collect(Collectors.toList());
    }


}
