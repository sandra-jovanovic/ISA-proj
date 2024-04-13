package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.Model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {
}
