package com.dhanashreej_phms.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService serv;
    @GetMapping("/")
    public String getLoginPage(@RequestParam String param) {
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,@RequestParam String password,@RequestParam String role,Model mod) {
        //TODO: process POST request
        Login user2=serv.log(username, password, role);
        if(user2!=null)
        {
            if ("Owner".equals(user2.getRole())) {
                return "redirect:/dashboard_owner";
            } else if ("Pharmacist".equals(user2.getRole())) {
                return "redirect:/dashboard_pharmacist"; 
            }
        }
        else{
            mod.addAttribute("error","Invalid credentials!!");
        }
        return "login";
    }
        
}
