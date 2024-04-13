package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Model.BloodTypes;
import com.example.ISA.tim6.Service.BloodTypesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class BloodTypesController {
    private final BloodTypesService bloodTypesService;

    public BloodTypesController(BloodTypesService bloodTypesService) {
        this.bloodTypesService = bloodTypesService;
    }

    @GetMapping(value = "/available-bloodTypes")
    public ResponseEntity<List<BloodTypes>> allAvailableBloodTypes() {return bloodTypesService.allAvailableBloodTypes();}
}
