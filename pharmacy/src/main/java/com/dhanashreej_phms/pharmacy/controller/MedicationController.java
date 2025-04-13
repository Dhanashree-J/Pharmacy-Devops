package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Medication;
import com.dhanashreej_phms.pharmacy.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MedicationController {

    @Autowired
    private MedicationService service;

    // View Inventory
    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        List<Medication> meds = service.getAll();
        model.addAttribute("medications", meds);
        return "inventory"; // Renders inventory.html
    }

    // Show Add Medication Form
    @GetMapping("/medication/add")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medication());
        return "add-product"; // Renders add-product.html
    }

    // Add Medication (POST)
    @PostMapping("/medication/add")
    public String addMedication(@ModelAttribute Medication med) {
        service.save(med);
        return "redirect:/inventory"; // Redirect to inventory page
    }

    // Show Delete Medication Form
    @GetMapping("/medication/delete")
    public String showDeleteMedicationForm() {
        return "delete-product"; // Renders delete-product.html
    }

    @GetMapping("/medication/update")
    public String showUpdateMedicationForm() {
        return "update-product"; // Renders delete-product.html
    }

    // Delete Medication (POST)
    @PostMapping("/delete-inventory")
    public String deleteMedication(@RequestParam String productName, Model model) {
        Medication med = service.getByName(productName);
        if (med != null) {
            service.delete(med.getId());
            return "redirect:/inventory"; // Redirect back to inventory after deletion
        } else {
            model.addAttribute("notFound", true);
            return "delete-product"; // Stay on delete-product page if not found
        }
    }

    // Search Medication for Update
    @PostMapping("/update-inventory")
    public String searchProduct(@RequestParam("searchProduct") String name, Model model) {
        Medication med = service.getByName(name);
        if (med != null) {
            model.addAttribute("medication", med);
        } else {
            model.addAttribute("notFound", true);
        }
        return "update-product"; // Renders update-product.html
    }

    // Update Medication (POST)
    @PostMapping("/update-product")
    public String updateProduct(@RequestParam String productName,
                                @RequestParam int quantity,
                                @RequestParam double price,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate) {
        Medication med = service.getByName(productName);
        if (med != null) {
            med.setStock(quantity);
            med.setPrice(price);
            med.setExpirationDate(expiryDate);
            service.save(med);
        }
        return "redirect:/inventory"; // Redirect to inventory after update
    }
    @GetMapping("/view")
    public String viewWholeInventory(Model model) {
        List<Medication> meds = service.getAll();
        model.addAttribute("medications", meds);
        return "view";
    }
    

}
