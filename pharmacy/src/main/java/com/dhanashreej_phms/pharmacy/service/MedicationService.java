package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Medication;
import com.dhanashreej_phms.pharmacy.repository.MedicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepo repo;

    public List<Medication> getAll() {
        return repo.findAll();
    }

    public void save(Medication med) {
        repo.save(med);
    }

    public Medication getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Medication getByName(String name) {
        return repo.findByName(name);
    }

    public List<Medication> getAllActive() {
        return repo.findByActiveTrue();
    }
    
    public int countLowStockItems(int threshold) {
        return (int) repo.findAll()
                                   .stream()
                                   .filter(med -> med.getStock() < threshold)
                                   .count();
    }
    public Medication findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    public int getLowStockCount() {
        return (int) repo.findAll().stream()
            .filter(m -> m.getStock() < 10) // or your threshold
            .count();
    }
    
    
    
}
