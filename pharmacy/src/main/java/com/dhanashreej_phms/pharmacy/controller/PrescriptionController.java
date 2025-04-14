package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Medication;
import com.dhanashreej_phms.pharmacy.domain.Prescription;
import com.dhanashreej_phms.pharmacy.dto.PrescriptionGroup;
import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.service.MedicationService;
import com.dhanashreej_phms.pharmacy.service.PrescriptionService;
import com.dhanashreej_phms.pharmacy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    List<Prescription> allPrescriptions = service.getAll();

    Map<String, List<Prescription>> grouped = allPrescriptions.stream()
        .collect(Collectors.groupingBy(p ->
            p.getPatientName() + "|" + 
            p.getDatePrescribed().withNano(0).toString() + "|" +
            (p.getPharmacist() != null ? p.getPharmacist().getUsername() : "N/A")
        ));

    List<PrescriptionGroup> groupedList = new ArrayList<>();
    for (Map.Entry<String, List<Prescription>> entry : grouped.entrySet()) {
        List<Prescription> groupItems = entry.getValue();
        PrescriptionGroup group = new PrescriptionGroup();
        group.setPatientName(groupItems.get(0).getPatientName());
        group.setDatePrescribed(groupItems.get(0).getDatePrescribed());
        group.setPharmacistUsername(groupItems.get(0).getPharmacist() != null 
                                     ? groupItems.get(0).getPharmacist().getUsername()
                                     : "N/A");
        group.setItems(groupItems);

        double total = groupItems.stream()
            .mapToDouble(p -> p.getMedication().getPrice() * p.getQuantity())
            .sum();
        group.setGrandTotal(total);

        groupedList.add(group);
    }

    model.addAttribute("prescriptionsGrouped", groupedList);
    return "prescription-list";
}


    @GetMapping("/prescriptions/add")
public String showPrescriptionForm(Model model) {
    model.addAttribute("medications", medService.getAllActive());
    return "prescription-form";
}  //Correct code

    @PostMapping("/prescriptions/add")
public String addMultipleMedicationsPrescription(
        @RequestParam String patientName,
        @RequestParam String pharmacistUsername,
        @RequestParam("medicationIds") List<Long> medicationIds,
        @RequestParam("quantities") List<Integer> quantities,
        Model model) {

    Login pharmacist = loginService.findByUsername(pharmacistUsername);
    if (pharmacist == null) {
        model.addAttribute("error", "Invalid pharmacist username.");
        model.addAttribute("medications", medService.getAll());
        return "prescription-form";
    }

    List<Prescription> prescriptions = new ArrayList<>();

    for (int i = 0; i < medicationIds.size(); i++) {
        Long medId = medicationIds.get(i);
        int qty = quantities.get(i);

        Medication med = medService.getById(medId);

        if (med.getExpirationDate() != null && med.getExpirationDate().isBefore(LocalDate.now())) {
            model.addAttribute("error", "Cannot prescribe expired medication: " + med.getName());
            model.addAttribute("medications", medService.getAll()); // Repopulate dropdown
            return "prescription-form"; 
        }

        if (med.getStock() < qty) {
            model.addAttribute("error", "Insufficient stock for: " + med.getName());
            model.addAttribute("medications", medService.getAll()); // Needed to repopulate dropdown
            return "prescription-form";
        }

        med.setStock(med.getStock() - qty);
        medService.save(med);

        Prescription prescription = new Prescription();
        prescription.setPharmacist(pharmacist); // ← important!

        prescription.setPatientName(patientName);
        prescription.setPharmacist(pharmacist);
        prescription.setMedication(med);
        prescription.setQuantity(qty);
        prescription.setDatePrescribed(LocalDateTime.now());

        service.save(prescription);
        prescriptions.add(prescription);
    }

    model.addAttribute("patientName", patientName);
    model.addAttribute("pharmacist", pharmacist);
    model.addAttribute("prescriptions", prescriptions);
    double grandTotal = prescriptions.stream()
    .mapToDouble(p -> p.getMedication().getPrice() * p.getQuantity())
    .sum();
    model.addAttribute("grandTotal", grandTotal);
    return "prescription-success";
}

}
