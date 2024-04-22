package com.rockbitegames.sandship.controller;

import com.rockbitegames.sandship.dto.MaterialTypeDto;
import com.rockbitegames.sandship.service.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/material-types")
public class MaterialTypeController {

    @Autowired
    private MaterialTypeService materialTypeService;

    @PostMapping
    public ResponseEntity<MaterialTypeDto> createMaterialType(@RequestBody MaterialTypeDto materialTypeDto) {
        return ResponseEntity.ok(materialTypeService.createNew(materialTypeDto));
    }

    @GetMapping
    public ResponseEntity<List<MaterialTypeDto>> getAllMaterialType() {
        return ResponseEntity.ok(materialTypeService.getAllMaterialTypes());
    }
}
