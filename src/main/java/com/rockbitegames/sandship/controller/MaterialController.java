package com.rockbitegames.sandship.controller;

import com.rockbitegames.sandship.dto.MaterialDto;
import com.rockbitegames.sandship.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDto> createMaterial(@RequestBody MaterialDto materialDto) {
        return ResponseEntity.ok(materialService.createNew(materialDto));
    }

    @GetMapping
    public ResponseEntity<List<MaterialDto>> getAllMaterials() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }
}

