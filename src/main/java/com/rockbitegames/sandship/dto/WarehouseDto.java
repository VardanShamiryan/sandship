package com.rockbitegames.sandship.dto;

import com.rockbitegames.sandship.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    String id;
    String name;
    User owner;
}
