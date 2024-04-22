package com.rockbitegames.sandship.service;

import com.rockbitegames.sandship.dto.MaterialTypeDto;
import com.rockbitegames.sandship.mapper.MaterialTypeMapper;
import com.rockbitegames.sandship.repository.MaterialTypeRepository;
import com.rockbitegames.sandship.validation.MaterialTypeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialTypeService {
    private MaterialTypeRepository materialTypeRepository;
    private MaterialTypeMapper mapper;
    private MaterialTypeValidator materialTypeValidator;


    public MaterialTypeDto createNew(MaterialTypeDto materialTypeDto) {
        materialTypeValidator.validate(materialTypeDto);
        return mapper.toDto(materialTypeRepository.save(mapper.toEntity(materialTypeDto)));
    }

    public List<MaterialTypeDto> getAllMaterialTypes() {
        return materialTypeRepository.findAll().stream()
                .map(materialType -> mapper.toDto(materialType))
                .collect(Collectors.toList());

    }
}
