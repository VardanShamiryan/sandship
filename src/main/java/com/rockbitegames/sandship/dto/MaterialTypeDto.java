package com.rockbitegames.sandship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialTypeDto {
    String id;
    String name;
    String description;
    String iconPath;
    Integer maxCapacity;
}
