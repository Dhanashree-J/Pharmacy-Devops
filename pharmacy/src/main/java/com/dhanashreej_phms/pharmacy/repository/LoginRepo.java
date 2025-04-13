package com.dhanashreej_phms.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhanashreej_phms.pharmacy.domain.Login;
@Repository

public interface LoginRepo extends JpaRepository<Login,String> {
    Login findByUsernameAndPasswordAndRole(String username,String password,String role);
}