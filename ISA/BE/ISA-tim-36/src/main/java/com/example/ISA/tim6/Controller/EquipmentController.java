package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Model.Equipment;
import com.example.ISA.tim6.Service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping(value = "/available-equipment")
    public ResponseEntity<List<Equipment>> allAvailableEquipment() {
        return equipmentService.allAvailableEquipment();
    }
}
