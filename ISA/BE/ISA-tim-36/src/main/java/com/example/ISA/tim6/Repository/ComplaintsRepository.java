package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.Model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintsRepository extends JpaRepository<Complaint, Long> {

}
