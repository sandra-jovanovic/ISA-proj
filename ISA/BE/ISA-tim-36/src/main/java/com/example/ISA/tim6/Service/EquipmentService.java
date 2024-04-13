package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Model.Equipment;
import com.example.ISA.tim6.Repository.EquipmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public ResponseEntity<List<Equipment>> allAvailableEquipment() {
        return ResponseEntity.ok(equipmentRepository.findAllByStockQuantityGreaterThan(0));
    }
}
