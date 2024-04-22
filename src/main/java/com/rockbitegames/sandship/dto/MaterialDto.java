package com.rockbitegames.sandship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {
    String id;
    String name;
    MaterialTypeDto materialTypeDto;
}
