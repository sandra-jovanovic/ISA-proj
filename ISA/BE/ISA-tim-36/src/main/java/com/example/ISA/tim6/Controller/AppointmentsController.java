package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Dtos.AppointmentSaveDto;
import com.example.ISA.tim6.Dtos.SaveReportDto;
import com.example.ISA.tim6.Model.Appointment;
import com.example.ISA.tim6.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AppointmentsController {

    private final AppointmentService appointmentService;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getCentres(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> getCentres(@Valid @RequestBody AppointmentSaveDto requestBody) {
        return appointmentService.saveAppointment(requestBody);
    }

    @PostMapping("/appointments/report")
    public ResponseEntity<?> saveReport(@Valid @RequestBody SaveReportDto requestBody) {
        return appointmentService.saveReport(requestBody);
    }

    @PostMapping("/appointments/registerUser/{appointmentId}")
    public ResponseEntity<?> registerUserToAppointment(@PathVariable Long appointmentId, @RequestBody Long userId) {
        System.out.println(appointmentId+"| |" +userId);
        return appointmentService.registerUserToAppointment(appointmentId, userId);
    }
}
