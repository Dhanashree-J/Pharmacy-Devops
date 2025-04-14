package com.dhanashreej_phms.pharmacy.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id")
    private Login pharmacist;

    private LocalDateTime datePrescribed;

    public Prescription(){
        
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Login getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Login pharmacist) {
        this.pharmacist = pharmacist;
    }

    public LocalDateTime getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDateTime datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    

    // Constructors, Getters & Setters
}
