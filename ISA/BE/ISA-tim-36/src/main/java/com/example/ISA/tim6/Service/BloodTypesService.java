package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Model.BloodTypes;
import com.example.ISA.tim6.Repository.BloodTypesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodTypesService {
    private final BloodTypesRepository bloodTypesRepository;

    public BloodTypesService(BloodTypesRepository bloodTypesRepository) {
        this.bloodTypesRepository = bloodTypesRepository;
    }

    public ResponseEntity<List<BloodTypes>> allAvailableBloodTypes() {
        return ResponseEntity.ok(bloodTypesRepository.findAllByStockQuantityGreaterThan(0));
    }
}
