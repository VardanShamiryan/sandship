package com.rockbitegames.sandship.mapper;

import com.rockbitegames.sandship.domain.MaterialType;
import com.rockbitegames.sandship.dto.MaterialTypeDto;
import org.springframework.stereotype.Component;

@Component
public class MaterialTypeMapper {
    public MaterialType toEntity(MaterialTypeDto dto) {
        MaterialType.MaterialTypeBuilder builder = MaterialType.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .iconPath(dto.getIconPath())
                .maxCapacity(dto.getMaxCapacity());
        return builder.build();
    }

    public MaterialTypeDto toDto(MaterialType entity) {
        MaterialTypeDto.MaterialTypeDtoBuilder builder = MaterialTypeDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .iconPath(entity.getIconPath())
                .maxCapacity(entity.getMaxCapacity());
        return builder.build();
    }

}


