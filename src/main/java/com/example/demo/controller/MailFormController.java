/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.ContactForm;
import com.example.demo.model.MailForm;
import com.example.demo.service.ContactFormService;
import com.example.demo.service.MailFormService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author lynda
 */

@Controller
public class MailFormController {
   @Autowired
    private MailFormService mailFormService;
    @Autowired
    private ContactFormService contactFormService;
     
   
     @GetMapping("/mailform")
    public String sendMail(Model model){
        model.addAttribute("mf", new MailForm());
        return "emailform";
        
    }
    
    @GetMapping("/mailform/{id}")
    public String sendMail(Model model, @PathVariable Long id){
        ContactForm con= contactFormService.getContactForm(id);
        con.setReplied(1);
        contactFormService.updateContactForm(con);
        MailForm mf= new MailForm();
        mf.setMail_to(con.getEmail());  
        model.addAttribute("mf", mf);
        
        return "emailform";
        
    }
     @PostMapping("/mailform")
    public String saveMailResponse(@ModelAttribute MailForm mf, Model model){
    mf.setDateReplied(new Date());
   
   mailFormService.addMailForm(mf);
    model.addAttribute("status", true);
    
    return "redirect:/mailform";
    
    }
    
    
   
}
