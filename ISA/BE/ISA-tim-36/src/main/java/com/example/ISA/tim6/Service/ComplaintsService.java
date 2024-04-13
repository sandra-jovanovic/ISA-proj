package com.example.ISA.tim6.Service;

import com.example.ISA.tim6.Model.Complaint;
import com.example.ISA.tim6.Repository.ComplaintsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintsService {

    private final ComplaintsRepository complaintsRepository;

    public ComplaintsService(ComplaintsRepository complaintsRepository) {
        this.complaintsRepository = complaintsRepository;
    }

    public List<Complaint> findComplaints() {
        return complaintsRepository.findAll();
    }

    public Optional<Complaint> getCompliantById(Long id) {
        return complaintsRepository.findById(id);
    }

    public void updateCompliant(Complaint complaint) {
        complaintsRepository.save(complaint);
    }
}
