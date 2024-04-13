package com.example.ISA.tim6.Repository;

import com.example.ISA.tim6.Model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findAllByStockQuantityGreaterThan(Integer minStock);
}
