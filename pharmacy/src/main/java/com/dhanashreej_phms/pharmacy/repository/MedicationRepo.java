package com.dhanashreej_phms.pharmacy.repository;

import com.dhanashreej_phms.pharmacy.domain.Medication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long> {
    Medication findByName(String name);
    List<Medication> findByActiveTrue();
}
