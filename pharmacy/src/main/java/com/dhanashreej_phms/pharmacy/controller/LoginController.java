package com.dhanashreej_phms.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dhanashreej_phms.pharmacy.domain.Login;
import com.dhanashreej_phms.pharmacy.repository.LoginRepo;
import com.dhanashreej_phms.pharmacy.service.LoginService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private LoginService serv;

    @Autowired
    private LoginRepo loginRepo;
    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPageRegister() {
        return "login";
    }

    @PostMapping("/login")
public String processLogin(@RequestParam String username, @RequestParam String password, 
                           @RequestParam String role, Model mod, HttpSession session) {
    Login user2 = serv.log(username, password, role);
    if (user2 != null) {
        session.setAttribute("loggedInUsername", user2.getUsername());
        session.setAttribute("role", user2.getRole());
        if ("Owner".equals(user2.getRole())) {
            return "redirect:/dashboard_owner";
        } else if ("Pharmacist".equals(user2.getRole())) {
            return "redirect:/dashboard_pharmacist";
        }
    } else {
        mod.addAttribute("error", "Invalid credentials!!");
    }
    return "login";
}
     // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Login());
        return "newuser";
    }

    // Process new user registration
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") Login login) {
        loginRepo.save(login);
        return "redirect:/login";
    }    
}
