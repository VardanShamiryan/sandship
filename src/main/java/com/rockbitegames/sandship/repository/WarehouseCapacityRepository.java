package com.rockbitegames.sandship.repository;

import com.rockbitegames.sandship.domain.WarehouseCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseCapacityRepository extends JpaRepository<WarehouseCapacity, String> {
    WarehouseCapacity findWarehouseCapacitiesByWarehouseIdAndMaterialTypeId(String warehouseId, String materialTypeId);
}

