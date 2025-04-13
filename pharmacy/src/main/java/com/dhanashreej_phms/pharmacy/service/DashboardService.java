package com.dhanashreej_phms.pharmacy.service;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.repository.DashboardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepo dashboardRepo;

    public Login getUserDetails(String username) {
        return dashboardRepo.findByUsername(username); // Fetch user details based on the username
    }
    

    // Add any additional methods here to fetch data for the dashboard
}
