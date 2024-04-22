package com.rockbitegames.sandship.service;

import com.rockbitegames.sandship.domain.Material;
import com.rockbitegames.sandship.domain.MaterialType;
import com.rockbitegames.sandship.domain.Warehouse;
import com.rockbitegames.sandship.domain.WarehouseCapacity;
import com.rockbitegames.sandship.dto.WarehouseDto;
import com.rockbitegames.sandship.mapper.WarehouseMapper;
import com.rockbitegames.sandship.repository.MaterialRepository;
import com.rockbitegames.sandship.repository.WarehouseCapacityRepository;
import com.rockbitegames.sandship.repository.WarehouseRepository;
import com.rockbitegames.sandship.validation.WarehouseValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseValidator warehouseValidator;
    private final WarehouseMapper mapper;
    private final WarehouseCapacityRepository capacityRepository;
    private final MaterialRepository materialRepository;

    public WarehouseDto createNew(WarehouseDto warehouseDto) {
        warehouseValidator.validate(warehouseDto);
        return mapper.toDto(warehouseRepository.save(mapper.toEntity(warehouseDto)));
    }

    public List<WarehouseDto> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> addMaterialToWarehouse(String warehouseId, String materialId, int quantity) {
        if (!materialRepository.existsById(materialId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Material doesn't exist.");
        }
        if (!warehouseRepository.existsById(warehouseId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Warehouse doesn't exist.");
        }

        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();
        WarehouseCapacity capacity = capacityRepository.findWarehouseCapacitiesByWarehouseIdAndMaterialTypeId(warehouseId, material.getMaterialType().getId());

        if (!checkWarehouseCapacityAvailability(material, quantity, capacity)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough capacity in warehouse.");
        }

        Map<Material, Integer> materials = warehouse.getMaterials();
        materials.put(material, materials.getOrDefault(material, 0) + quantity);
        warehouse.setMaterials(materials);
        warehouseRepository.save(warehouse);

        if (capacity == null) {
            capacity = WarehouseCapacity.builder()
                    .warehouse(warehouse)
                    .materialType(material.getMaterialType())
                    .capacity(quantity)
                    .build();
        } else {
            capacity.setCapacity(capacity.getCapacity() + quantity);
        }
        capacityRepository.save(capacity);
        return ResponseEntity.ok().body("Material added to warehouse.");
    }

    public ResponseEntity<String> removeMaterialFromWarehouse(String warehouseId, String materialId, int quantity) {

        if (!materialRepository.existsById(materialId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Material doesn't exist.");
        }
        if (!warehouseRepository.existsById(warehouseId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Warehouse doesn't exist.");
        }

        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();
        Material material = materialRepository.findById(materialId).orElseThrow();
        WarehouseCapacity capacity = capacityRepository.findWarehouseCapacitiesByWarehouseIdAndMaterialTypeId(warehouseId, material.getMaterialType().getId());
        Map<Material, Integer> materials = warehouse.getMaterials();

        if (materials.get(material) < quantity) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Not enough material in this warehouse.");
        }
        materials.put(material, materials.get(material) - quantity);
        warehouseRepository.save(warehouse);
        capacity.setCapacity(capacity.getCapacity() - quantity);
        capacityRepository.save(capacity);

        return ResponseEntity.ok().body("Material removed from warehouse.");
    }

    @Transactional
    public ResponseEntity<String> moveMaterialFromWarehouseToWarehouse(String fromWarehouseId, String toWarehouseId,
                                                                       String materialId, int quantity) {
        try {
            if (!materialRepository.existsById(materialId)) {
                throw new RuntimeException("Material doesn't exist.");
            }
            if (!warehouseRepository.existsById(fromWarehouseId)) {
                throw new RuntimeException("fromWarehouse doesn't exist.");
            }
            if (!warehouseRepository.existsById(toWarehouseId)) {
                throw new RuntimeException("toWarehouse doesn't exist.");
            }

            ResponseEntity<String> removeResponse = removeMaterialFromWarehouse(fromWarehouseId, materialId, quantity);
            ResponseEntity<String> addResponse = addMaterialToWarehouse(toWarehouseId, materialId, quantity);

            if (removeResponse.getStatusCode() == HttpStatus.OK && addResponse.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok().body("Material moved between warehouses.");
            } else {
                throw new RuntimeException("Failed to move material between warehouses.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to move material between warehouses.", e);
        }
    }


    private boolean checkWarehouseCapacityAvailability(Material material, int quantity, WarehouseCapacity capacity) {
        MaterialType materialType = material.getMaterialType();
        if (capacity == null) {
            return quantity < materialType.getMaxCapacity();
        } else return capacity.getCapacity() + quantity <= materialType.getMaxCapacity();
    }
}
