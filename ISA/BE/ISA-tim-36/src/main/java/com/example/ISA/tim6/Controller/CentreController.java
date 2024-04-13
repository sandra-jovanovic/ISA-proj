package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Dtos.AppointmentSearchDTO;
import com.example.ISA.tim6.Dtos.CentreSaveDto;
import com.example.ISA.tim6.Model.Appointment;
import com.example.ISA.tim6.Model.Centre;
import com.example.ISA.tim6.Service.AppointmentService;
import com.example.ISA.tim6.Service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CentreController {

    @Autowired
    private CentreService centreService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/centres")
    public ResponseEntity<List<Centre>> getCentres() {
        return centreService.getAllCentres();
    }

    @GetMapping("/centres/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByCentre(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByCentre(id));
    }

    @GetMapping("/centres/{id}")
    public ResponseEntity<Centre> getCentreById(@PathVariable Long id) {
        return centreService.getCentreById(id);
    }

    @PostMapping("/centres")
    public ResponseEntity<Centre> createCentre(@RequestBody CentreSaveDto dto) {
        return centreService.addNewCentre(dto);
    }

    @PutMapping("/centres/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable("id") long id, @RequestBody Centre centre) {
        return centreService.updateCentre(id, centre);
    }

    @PostMapping("/centres/getbytime")
    public ResponseEntity<List<Centre>> getCentersByTime(@RequestBody AppointmentSearchDTO dto) {
        return centreService.searchCentersByAppointmentTime(dto);
    }
}
