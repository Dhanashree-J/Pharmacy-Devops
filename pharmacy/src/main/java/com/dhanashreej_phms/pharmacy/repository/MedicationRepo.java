package com.dhanashreej_phms.pharmacy.repository;

import com.dhanashreej_phms.pharmacy.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepo extends JpaRepository<Medication, Long> {
    Medication findByName(String name);
}
