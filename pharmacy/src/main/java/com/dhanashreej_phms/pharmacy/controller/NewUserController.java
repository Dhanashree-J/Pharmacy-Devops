package com.dhanashreej_phms.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dhanashreej_phms.pharmacy.service.NewUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class NewUserController {
    @Autowired
    private NewUserService service;
    @GetMapping("/newuser")
    public String showNewUserPage(){
        return "newuser";
    }
    @PostMapping("path")
    public String newuser(@RequestBody String username,@RequestParam password,@RequestParam role,Model mod) {
        //TODO: process POST request
        boolean isregistered=service.register(username,password,role);
        if(isregistered)
        {
            mod.addAttribute("message","Successful..Please login");
            return "login";
        }
        else
        {
            mod.addAttribute("error","User already exists");
            return "newuser";
        }
        
        return entity;
    }
    
    
}
