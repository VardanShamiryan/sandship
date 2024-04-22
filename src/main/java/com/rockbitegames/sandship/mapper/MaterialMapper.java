package com.rockbitegames.sandship.mapper;

import com.rockbitegames.sandship.domain.Material;
import com.rockbitegames.sandship.domain.MaterialType;
import com.rockbitegames.sandship.dto.MaterialDto;
import com.rockbitegames.sandship.dto.MaterialTypeDto;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public Material toEntity(MaterialDto dto) {
        Material.MaterialBuilder builder = Material.builder()
                .name(dto.getName())
                .materialType(MaterialType.builder()
                        .id(dto.getMaterialTypeDto().getId())
                        .description(dto.getMaterialTypeDto().getDescription())
                        .name(dto.getMaterialTypeDto().getName())
                        .maxCapacity(dto.getMaterialTypeDto().getMaxCapacity())
                        .build());
        return builder.build();
    }

    public MaterialDto toDto(Material entity) {
        MaterialDto.MaterialDtoBuilder builder = MaterialDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .materialTypeDto(MaterialTypeDto.builder()
                        .id(entity.getMaterialType().getId())
                        .description(entity.getMaterialType().getDescription())
                        .name(entity.getMaterialType().getName())
                        .maxCapacity(entity.getMaterialType().getMaxCapacity())
                        .build());
        return builder.build();
    }

}


