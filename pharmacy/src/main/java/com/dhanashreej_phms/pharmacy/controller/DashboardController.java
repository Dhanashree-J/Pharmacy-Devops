package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.service.DashboardService;
import com.dhanashreej_phms.pharmacy.service.MedicationService;
import com.dhanashreej_phms.pharmacy.service.PrescriptionService;
import com.dhanashreej_phms.pharmacy.service.SaleService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private MedicationService medicationService;

    // Endpoint for Owner Dashboard
    @GetMapping("/dashboard_owner")
public String showOwnerDashboard(HttpSession session, Model model) {
    String username = (String) session.getAttribute("loggedInUsername");
    Login owner = dashboardService.getUserDetails(username);
    model.addAttribute("owner", owner);
    return "dashboard_owner";
}



    // Endpoint for Pharmacist Dashboard
    @GetMapping("/dashboard_pharmacist")
    public String showPharmacistDashboard(Model model) {
        // Fetch user details for the pharmacist
        Login pharmacist = dashboardService.getUserDetails("pharmacistUsername");
        int totalPrescriptions = prescriptionService.getAll().size(); // or a specific method if you have
        int lowStockCount = medicationService.getLowStockCount(); // you need this method
        int customersServed = prescriptionService.getUniqueCustomerCount(); // implement if needed
        model.addAttribute("totalPrescriptions", totalPrescriptions);
        model.addAttribute("lowStockCount", lowStockCount);
        model.addAttribute("customersServed", customersServed);
        model.addAttribute("pharmacist", pharmacist);
        return "dashboard_pharmacist";  // Returns the dashboard_pharmacist.html page
    }
        @GetMapping("/dashboard")
        public String dashboardRedirect(HttpSession session,Model model) {
            String role = (String) session.getAttribute("role");
    
            if ("Owner".equalsIgnoreCase(role)) {
                return "dashboard_owner"; // HTML page for Owner
            } else if ("Pharmacist".equalsIgnoreCase(role)) {
                double totalSales = saleService.getTotalSales();
    int totalPrescriptions = prescriptionService.getAll().size(); // or a specific method if you have
    int lowStockCount = medicationService.getLowStockCount(); // you need this method
    int customersServed = prescriptionService.getUniqueCustomerCount(); // implement if needed

    model.addAttribute("totalSales", totalSales);
    model.addAttribute("totalPrescriptions", totalPrescriptions);
    model.addAttribute("lowStockCount", lowStockCount);
    model.addAttribute("customersServed", customersServed);

                return "dashboard_pharmacist"; // HTML page for Pharmacist
            } else {
                return "login"; // fallback if no role found
            }
        }
    
}
