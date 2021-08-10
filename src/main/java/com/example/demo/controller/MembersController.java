/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Members;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.MembersService;
import com.example.demo.service.VerificationTokenService;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author lynda
 */
public class MembersController {
      @Value("${files_uploading_directory}")
    public String upload_directory;
 @Value("${reg_message}")
    public String reg_message;

    @Autowired 
           private MembersService membersService;
       @Autowired
    private VerificationTokenService verificationTokenService;
      @Autowired
     private AuthorityRepository authorityRepository;
    
       
      @Autowired
     private EmailService emailService;

      @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("mem", new Members());
        return "register";
}
    
    @PostMapping("/register")
    public String SaveRegister(@ModelAttribute Members mem, Model model, HttpServletRequest request
) {
          if (mem.getPassword().equals(mem.getConfirmPassword())== false ){
           model.addAttribute("error", "passwords do not match") ;
        model.addAttribute("mem", mem);
        
        return "register";
            }
          
        Members newMem= membersService.getMember(mem.getEmail());
        if (newMem != null){
        model.addAttribute("message", "This email already exists");
        return "baduser";
        }
        else{
       mem.setDateRegistered(new Date());
       mem.setUnit("");
       mem.setRoles("");
      // Set<Authorities> autho= new HashSet<>();
     //  autho.add(authorityRepository.findByAuthorityName("ROLE_MEMBER"));
       mem.getAuthority().add(authorityRepository.findByAuthorityName("ROLE_MEMBER"));
      mem= membersService.addMembers(mem);
      VerificationToken vt= new VerificationToken();
      vt.setMember(mem);
      String token = UUID.randomUUID().toString();
      
        vt.setToken(token);
        verificationTokenService.createToken(vt);
       String appUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

      //  String appUrl = request.getContextPath();
        String recipientAddress = mem.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = appUrl + "/register/registrationConfirm?token=" + token;
        String message =reg_message+confirmationUrl;
        emailService.sendMail(recipientAddress, subject, message);

       //  membersService.addMembers(mem);
         model.addAttribute("status", true);
        return "redirect:/register";
}
    } 
    
    @GetMapping("register/registrationConfirm")
public String confirmRegistration
  ( Model model, @RequestParam("token") String token) {
 
    VerificationToken verificationToken = verificationTokenService.getToken(token);
    if (verificationToken == null) {

        model.addAttribute("message", "Invalid Token");
        return "baduser";
    }
    
  Members mem = verificationToken.getMember();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
      
        model.addAttribute("message", "Expired Token");
        return "baduser";
    } 
    
   mem.setVerified(true); 
   membersService.updateMembers(mem); 
    return "verified";
}
    
    
    
    @RequestMapping(value = "/viewmembers", method = RequestMethod.GET)
    public String ViewMembers(Model model) {
        model.addAttribute("allmembers", membersService.getAllMembers());
        return "allmembers";
}
}
