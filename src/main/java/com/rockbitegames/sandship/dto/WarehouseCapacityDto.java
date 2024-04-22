package com.rockbitegames.sandship.dto;

import com.rockbitegames.sandship.domain.MaterialType;
import com.rockbitegames.sandship.domain.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseCapacityDto {
    String id;
    Warehouse warehouse;
    MaterialType materialType;
    int capacity;
}
