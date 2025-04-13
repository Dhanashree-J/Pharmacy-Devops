package com.dhanashreej_phms.pharmacy.controller;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.service.DashboardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

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
        Login pharmacist = dashboardService.getUserDetails("pharmacistUsername");  // Replace with actual username
        model.addAttribute("pharmacist", pharmacist);
        return "dashboard_pharmacist";  // Returns the dashboard_pharmacist.html page
    }
//     @GetMapping("/inventory")
// public String inventoryPage() {
//     return "inventory";
//}

}
