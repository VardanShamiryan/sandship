package com.rockbitegames.sandship.controller;

import com.rockbitegames.sandship.dto.WarehouseDto;
import com.rockbitegames.sandship.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WarehouseDto> createWarehouse(@RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok(warehouseService.createNew(warehouseDto));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @PostMapping("/addMaterialToStore")
    public ResponseEntity<String> addMaterialToWarehouse(@RequestParam String warehouseId,
                                                         @RequestParam String materialId,
                                                         @RequestParam Integer quantity) {
        return warehouseService.addMaterialToWarehouse(warehouseId, materialId, quantity);
    }

    @PostMapping("/removedMaterialFromStore")
    public ResponseEntity<String> removeMaterialFromWarehouse(@RequestParam String warehouseId,
                                                         @RequestParam String materialId,
                                                         @RequestParam Integer quantity) {
        return warehouseService.removeMaterialFromWarehouse(warehouseId, materialId, quantity);
    }

    @PostMapping("/moveMaterial")
    public ResponseEntity<String> moveMaterialFromWarehouseToWarehouse(@RequestParam String fromWarehouseId,
                                                                       @RequestParam String toWarehouseId,
                                                              @RequestParam String materialId,
                                                              @RequestParam Integer quantity) {
        return warehouseService.moveMaterialFromWarehouseToWarehouse(fromWarehouseId, toWarehouseId, materialId, quantity);
    }
}
