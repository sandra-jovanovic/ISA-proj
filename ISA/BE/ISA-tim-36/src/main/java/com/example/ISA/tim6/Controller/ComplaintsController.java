package com.example.ISA.tim6.Controller;

import com.example.ISA.tim6.Dtos.ComplaintResponseDto;
import com.example.ISA.tim6.Dtos.CompliantRequestDto;
import com.example.ISA.tim6.Model.Complaint;
import com.example.ISA.tim6.Service.ComplaintsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ComplaintsController {

    private final ComplaintsService complaintsService;

    public ComplaintsController(ComplaintsService complaintsService) {
        this.complaintsService = complaintsService;
    }

    @GetMapping(path = "/complaints")
    public ResponseEntity<List<ComplaintResponseDto>> findComplaints() {
        List<Complaint> complaints = complaintsService.findComplaints();
        var dtos = complaints.stream().map(c -> new ComplaintResponseDto(c.getId(), c.getCentre().getId(), c.getCentre().getName(),
                c.getMedicalWorker().getId(), c.getMedicalWorker().getName(), c.getMedicalWorker().getSurname(),
                c.getUser().getId(), c.getUser().getName(), c.getUser().getSurname(),
                c.getDescription(), c.getAnswer())).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PutMapping(path = "/complaints")
    public ResponseEntity saveCompliantAnswer(@Valid @RequestBody CompliantRequestDto requestBody) {
        var optional = complaintsService.getCompliantById(requestBody.getComplaintId());
        if (optional.isPresent()) {
            var compliant = optional.get();
            compliant.setAnswer(requestBody.getAnswer());
            complaintsService.updateCompliant(compliant);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
