package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Prescription;
import com.dhanashreej_phms.pharmacy.domain.Sale;
import com.dhanashreej_phms.pharmacy.dto.SalesSummary;
import com.dhanashreej_phms.pharmacy.repository.PrescriptionRepo;
import com.dhanashreej_phms.pharmacy.repository.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepo repo;

    @Autowired
    private PrescriptionRepo prescriptionRepository;

    public List<Sale> getAll() {
        return repo.findAll();
    }

    public void save(Sale sale) {
        repo.save(sale);
    }

    public double getTotalSales() {
        return repo.findAll()
                   .stream()
                   .mapToDouble(Sale::getTotalPrice)
                   .sum();
    }

    public List<SalesSummary> getDailySalesSummary() {
        List<Prescription> allPrescriptions = prescriptionRepository.findAll();
    
        Map<LocalDate, Map<String, List<Prescription>>> grouped = allPrescriptions.stream()
            .collect(Collectors.groupingBy(
                p -> p.getDatePrescribed().toLocalDate(),
                Collectors.groupingBy(p -> p.getPharmacist().getUsername())
            ));
    
        List<SalesSummary> summaryList = new ArrayList<>();
    
        for (Map.Entry<LocalDate, Map<String, List<Prescription>>> dateEntry : grouped.entrySet()) {
            LocalDate date = dateEntry.getKey();
            for (Map.Entry<String, List<Prescription>> pharmacistEntry : dateEntry.getValue().entrySet()) {
                String pharmacist = pharmacistEntry.getKey();
                List<Prescription> prescriptions = pharmacistEntry.getValue();
    
                int totalPrescriptions = prescriptions.size();
                double totalRevenue = prescriptions.stream()
                    .mapToDouble(p -> p.getMedication().getPrice() * p.getQuantity())
                    .sum();
    
                // Make sure you pass the pharmacist username
                summaryList.add(new SalesSummary(date, pharmacist, totalPrescriptions, totalRevenue, null));
            }
        }
    
        return summaryList;
    }
    
}
