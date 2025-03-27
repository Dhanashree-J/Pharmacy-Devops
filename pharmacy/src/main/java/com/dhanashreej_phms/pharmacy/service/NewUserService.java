package com.dhanashreej_phms.pharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.repository.NewUserRepo;

@Service
public class NewUserService {
    @Autowired
    private NewUserRepo repo;
    public boolean register(String username,String password,String role)
    {
        if(repo.findByUsername(username)!=null)
        {
            return false;
        }
        Login newuser=new Login(username, password,role);
        repo.save(newuser); //Insted of writing query of insert, we are using SAVE
        return true;
    }
    
}
