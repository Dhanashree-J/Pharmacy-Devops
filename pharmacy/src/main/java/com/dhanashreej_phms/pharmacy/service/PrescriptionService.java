package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Prescription;
import com.dhanashreej_phms.pharmacy.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepo repo;

    public List<Prescription> getAll() {
        List<Prescription> prescriptions = repo.findAll();
        return prescriptions;
    }

    public void save(Prescription p) {
        repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public int getTotalCount() {
        return (int) repo.count();
    }
    
}
