package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Prescription;
import com.dhanashreej_phms.pharmacy.domain.Sale;
import com.dhanashreej_phms.pharmacy.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepo repo;

    @Autowired
private SaleService saleService;

public void savePrescription(Prescription prescription) {
    repo.save(prescription);

    // Create a Sale
    Sale sale = new Sale();
    sale.setDate(LocalDateTime.now());
    sale.setTotalPrice(prescription.getMedication().getPrice() * prescription.getQuantity());
    sale.setPharmacist(prescription.getPharmacist()); // if pharmacist is linked
    saleService.save(sale);
}


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

    public int getUniqueCustomerCount() {
        return (int) repo.findAll().stream()
            .map(Prescription::getPatientName)
            .distinct()
            .count();
    }
    
    
}
