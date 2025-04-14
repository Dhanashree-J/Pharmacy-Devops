package com.dhanashreej_phms.pharmacy.dto;

import java.time.LocalDate;
import java.util.Map;

public class SalesSummary {
    private LocalDate date;
    private String pharmacistUsername; // Add this field
    private int totalPrescriptions;
    private double totalRevenue;
    private Map<String, Double> revenueByPharmacist;

    public SalesSummary() { }

    public SalesSummary(LocalDate date, String pharmacistUsername, int totalPrescriptions, 
                        double totalRevenue, Map<String, Double> revenueByPharmacist) {
        this.date = date;
        this.pharmacistUsername = pharmacistUsername;  // Initialize the username here
        this.totalPrescriptions = totalPrescriptions;
        this.totalRevenue = totalRevenue;
        this.revenueByPharmacist = revenueByPharmacist;
    }

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getPharmacistUsername() { return pharmacistUsername; }  // Add getter for pharmacistUsername
    public void setPharmacistUsername(String pharmacistUsername) { this.pharmacistUsername = pharmacistUsername; }

    public int getTotalPrescriptions() { return totalPrescriptions; }
    public void setTotalPrescriptions(int totalPrescriptions) { this.totalPrescriptions = totalPrescriptions; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    public Map<String, Double> getRevenueByPharmacist() { return revenueByPharmacist; }
    public void setRevenueByPharmacist(Map<String, Double> revenueByPharmacist) { this.revenueByPharmacist = revenueByPharmacist; }
}
