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

    // @Autowired
    // private LoginService loginService;

    //View Inventory
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
            med.setActive(false);
            service.save(med);
            return "redirect:/inventory"; // Redirect back to inventory after deletion
        } else {
            model.addAttribute("notFound", true);
            return "delete-product"; // Stay on delete-product page if not found
        }
    }

    //With Error Logs
    @PostMapping("/update-inventory")
public String searchProduct(@RequestParam("searchProduct") String name, Model model) {
    System.out.println("Searching for: " + name);
    Medication med = service.getByName(name);
    if (med != null) {
        model.addAttribute("medication", med);
        System.out.println("Found: " + med.getName());
    } else {
        model.addAttribute("notFound", true);
        System.out.println("Not found.");
    }
    return "update-product";
}

@PostMapping("/update-product")
public String updateProduct(@RequestParam Long id,
                            @RequestParam int quantity,
                            @RequestParam double price,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expiryDate) {
    Medication med = service.getById(id);
    if (med != null) {
        med.setStock(quantity);
        med.setPrice(price);
        med.setExpirationDate(expiryDate);
        service.save(med);
    }
    return "redirect:/inventory";
}
    @GetMapping("/view")
    public String viewWholeInventory(Model model) {
        List<Medication> meds = service.getAllActive();
        model.addAttribute("medications", meds);
        return "view";
    }
    

}
