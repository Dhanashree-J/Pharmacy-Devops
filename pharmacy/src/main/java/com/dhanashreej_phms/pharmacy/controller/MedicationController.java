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

    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        List<Medication> meds = service.getAll();
        model.addAttribute("medications", meds);
        return "inventory";
    }

    @PostMapping("/medication/add")
    public String addMedication(@ModelAttribute Medication med) {
        service.save(med);
        return "redirect:/inventory";
    }

    @GetMapping("/medication/delete/{id}")
    public String deleteMedication(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/inventory";
    }
    @PostMapping("/update-inventory")
public String searchProduct(@RequestParam("searchProduct") String name, Model model) {
    Medication med = service.getByName(name);
    if (med != null) {
        model.addAttribute("medication", med);
    } else {
        model.addAttribute("notFound", true);
    }
    return "update-product";
}

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
    return "redirect:/inventory";
}

}
