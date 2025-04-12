package com.dhanashreej_phms.pharmacy.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Medication medication;

    private int quantity;
    private double totalPrice;

    @ManyToOne
    private Login pharmacist;

    private String paymentMethod;
    private LocalDateTime saleDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Login getPharmacist() {
        return pharmacist;
    }
    public void setPharmacist(Login pharmacist) {
        this.pharmacist = pharmacist;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    // Constructors, Getters & Setters
}
