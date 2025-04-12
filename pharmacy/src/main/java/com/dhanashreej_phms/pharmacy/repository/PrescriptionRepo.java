package com.dhanashreej_phms.pharmacy.repository;

import com.dhanashreej_phms.pharmacy.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
}
