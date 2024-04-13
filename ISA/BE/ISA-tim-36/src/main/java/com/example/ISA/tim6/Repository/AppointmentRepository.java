package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.Model.Appointment;
import com.example.ISA.tim6.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCentreId(Long centreId);

    Optional<Appointment> findByDateTimeBetweenAndMedicalWorker(LocalDateTime start, LocalDateTime end, User medicalWorker);
}
