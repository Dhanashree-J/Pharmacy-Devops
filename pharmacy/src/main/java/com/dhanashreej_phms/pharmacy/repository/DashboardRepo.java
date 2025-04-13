package com.dhanashreej_phms.pharmacy.repository;

import com.dhanashreej_phms.pharmacy.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepo extends JpaRepository<Login, String> {
    // You can define custom queries if needed, for example:
    Login findByUsername(String username); // Fetch user by their username
}
