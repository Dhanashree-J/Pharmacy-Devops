// src/main/java/com/dhanashreej_phms/pharmacy/dto/PrescriptionGroup.java
package com.dhanashreej_phms.pharmacy.dto;

import com.dhanashreej_phms.pharmacy.domain.Prescription;
import java.time.LocalDateTime;
import java.util.List;

public class PrescriptionGroup {
    private String patientName;
    private LocalDateTime datePrescribed;
    private List<Prescription> items;

    private String pharmacistUsername;

    private double grandTotal;

    // Constructor
    public PrescriptionGroup(){
        
    }
    public PrescriptionGroup(String patientName, LocalDateTime datePrescribed, List<Prescription> items) {
        this.patientName = patientName;
        this.datePrescribed = datePrescribed;
        this.items = items;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDatePrescribed(LocalDateTime datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    public void setItems(List<Prescription> items) {
        this.items = items;
    }
    public double getGrandTotal() {
        return grandTotal;
    }
    
    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
    
    // Getters
    public String getPatientName() { 
        return patientName; 
    }
    public LocalDateTime getDatePrescribed() { 
        return datePrescribed;
     }
    public List<Prescription> getItems() {
         return items; 
        }
    public String getPharmacistUsername() {
        return pharmacistUsername;
    }
    public void setPharmacistUsername(String pharmacistUsername) {
        this.pharmacistUsername = pharmacistUsername;
    }
}
