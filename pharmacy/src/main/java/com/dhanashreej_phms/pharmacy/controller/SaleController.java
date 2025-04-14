package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.domain.Medication;
import com.dhanashreej_phms.pharmacy.domain.Sale;
import com.dhanashreej_phms.pharmacy.dto.SalesSummary;
import com.dhanashreej_phms.pharmacy.service.LoginService;
import com.dhanashreej_phms.pharmacy.service.MedicationService;
import com.dhanashreej_phms.pharmacy.service.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService service;

    @Autowired
    private MedicationService medService;

    @Autowired
    private LoginService loginService;

        @GetMapping("/sales")
    public String viewSalesSummary(Model model) {
        List<SalesSummary> salesSummary = service.getDailySalesSummary();
        model.addAttribute("salesSummary", salesSummary);
        return "sales";
    }

    @PostMapping("/sales/add")
    public String addSale(@RequestParam Long medicationId,
                          @RequestParam int quantity,
                          @RequestParam String paymentMethod,
                          @RequestParam String pharmacistUsername) {
        Medication med = medService.getById(medicationId);
        Login pharmacist = loginService.findByUsername(pharmacistUsername);

        double total = med.getPrice() * quantity;
        Sale sale = new Sale();
        sale.setTotalPrice(total);
        sale.setPharmacist(pharmacist);
        sale.setDate(LocalDateTime.now());

        med.setStock(med.getStock() - quantity); // update inventory
        medService.save(med);
        service.save(sale);

        return "redirect:/sales";
    }
}
