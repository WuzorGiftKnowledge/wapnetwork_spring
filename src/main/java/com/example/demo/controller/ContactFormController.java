/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

/**
 *
 * @author lynda
 */
import com.example.demo.model.ContactForm;
import com.example.demo.service.ContactFormService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactFormController {
    @Autowired
    private ContactFormService contactFormService;
    @Value("${files_uploading_directory}")
    public String upload_directory;
    
      @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("cf", new ContactForm());
        return "contact";
    }
    @PostMapping("/contact")
    public String saveContact(@ModelAttribute ContactForm cf, Model model){
        cf.setReplied(0);
        cf.setDateAdded(new Date());
  
        contactFormService.addContactForm(cf);
        model.addAttribute("status", true);
    return "redirect:/contact";
    }
       
   @GetMapping("/all_contact")
   public String AllContact(Model model){
   model.addAttribute("allcontact", contactFormService.getAllContactForm());
   return "allcontact";
     
   }
   
   
    
    
}
