package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.Model.BloodTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodTypesRepository extends JpaRepository<BloodTypes, Long> {

    List<BloodTypes> findAllByStockQuantityGreaterThan(Integer minStock);
}
