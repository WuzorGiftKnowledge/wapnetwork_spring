/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.ContactForm;


import com.example.demo.model.Testimony;
import com.example.demo.model.Donation;
import com.example.demo.model.Image;
import com.example.demo.model.MailForm;
import com.example.demo.model.Members;
import com.example.demo.model.Program;
import com.example.demo.model.Sermon;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.ContactFormService;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author babafemi
 */
@Controller
public class MainController {

    
    
    
     
        
    @GetMapping("/index2")
    public String index2(Model model){
        return "index2";
    }
        
     @GetMapping("/dashboard")
    public String dashboard(){
        return "Ample-admin/dashboard";
    }
    
   
    
     @GetMapping("/login")
    public String login() {
        return "login";
} 
    
    
    
     @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
         return "redirect:/login";
    }
  
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){    
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout=true";
//    }
//    
    
    
   
    
     




//    @PostMapping("/blog")
//    public String saveBlog(@ModelAttribute Blog bl, Model model, @RequestParam("blogImage") MultipartFile blogImage){
//   bl.setDateupdated(new Date());
//   bl.setApproved(0);
//
//    if(blogImage.getOriginalFilename().isEmpty()){
//        //continue;
//    }else{
//        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
//        Path path = Paths.get(upload_directory + fileName);
//        try {
//            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/download/")
//                .path(fileName)
//                .toUriString();
//    
//        Image image= new Image(bl.getTitle(),  fileDownloadUri);
//        bl.setBImage(image);
//    }
//
//  
//
//
//
//       blogService.addBlog(bl);
//    model.addAttribute("status", true);
//    
//    return "redirect:/blog";
//    
//    }
    
    

//        @GetMapping("/mailform")
//    public String mailForm(){
//        ContactForm con= contactFormService.getContactForm(id);
//        model.addAttribute("con", con);
//        
//        return "emailform";
//        
//    }

   
 
    }
    
   

