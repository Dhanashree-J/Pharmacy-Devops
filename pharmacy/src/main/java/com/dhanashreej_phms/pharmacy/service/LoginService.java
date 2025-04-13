package com.dhanashreej_phms.pharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.repository.LoginRepo;

@Service
public class LoginService {
    @Autowired
    private LoginRepo rep;
    public Login log(String username,String password,String role){
        return rep.findByUsernameAndPasswordAndRole(username,password,role);
    
    }
    public Login findByUsername(String username){
        return rep.findById(username).orElse(null);
    }
}