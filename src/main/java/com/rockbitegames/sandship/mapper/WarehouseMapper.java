package com.rockbitegames.sandship.mapper;

import com.rockbitegames.sandship.domain.User;
import com.rockbitegames.sandship.domain.Warehouse;
import com.rockbitegames.sandship.dto.WarehouseDto;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public Warehouse toEntity(WarehouseDto dto) {
        Warehouse.WarehouseBuilder builder = Warehouse.builder()
                .name(dto.getName())
                .owner(User.builder()
                        .name(dto.getOwner().getName())
                        .id(dto.getOwner().getId())
                        .email(dto.getOwner().getEmail())
                        .build());
        return builder.build();
    }

    public WarehouseDto toDto(Warehouse entity) {
        WarehouseDto.WarehouseDtoBuilder builder = WarehouseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .owner(entity.getOwner());
        return builder.build();
    }

}
