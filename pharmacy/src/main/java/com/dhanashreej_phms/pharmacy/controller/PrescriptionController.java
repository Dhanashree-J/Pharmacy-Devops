package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Medication;
import com.dhanashreej_phms.pharmacy.domain.Prescription;
import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.service.MedicationService;
import com.dhanashreej_phms.pharmacy.service.PrescriptionService;
import com.dhanashreej_phms.pharmacy.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    @Autowired
    private MedicationService medService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/prescriptions")
    public String listPrescriptions(Model model) {
        List<Prescription> list = service.getAll();
        model.addAttribute("prescriptions", list);
        model.addAttribute("medications", medService.getAll());
        return "prescription-list";
    }

    @PostMapping("/prescriptions/add")
    public String addPrescription(@RequestParam String patientName,
                                  @RequestParam Long medicationId,
                                  @RequestParam int quantity,
                                  @RequestParam String pharmacistUsername) {
        Medication med = medService.getById(medicationId);
        Login pharmacist = loginService.findByUsername(pharmacistUsername);

        Prescription p = new Prescription();
        p.setPatientName(patientName);
        p.setMedication(med);
        p.setQuantity(quantity);
        p.setPharmacist(pharmacist);
        p.setDatePrescribed(LocalDateTime.now());

        med.setStock(med.getStock() - quantity); // update inventory
        medService.save(med);
        service.save(p);
        return "redirect:/prescriptions";
    }

}
