/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Donation;
import com.example.demo.payStack.VerifyTransactionResponse;
import com.example.demo.repository.DonationRepository;
import com.example.demo.service.DonationService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lynda
 */

@Controller
public class DonationController {
     @Autowired 
             private DonationService donationService;
     
       @Autowired 
      DonationRepository donationRepository;
     
       @GetMapping("/donation")
    public String donation(Model model) {
        model.addAttribute("don",new Donation());
        return "donation";
}

@PostMapping("/donation")
    public String saveDonation(@ModelAttribute Donation don, Model model){
    don.setDateDonated(new Date());
    don.setDonationMethod("Bank Transfer");
    don.setApproved(0);
    donationService.addDonation(don);
    model.addAttribute("status", true);
    
    return "redirect:/donation";
    
    }
    
    @GetMapping("/all_donations")
   public String AllDonations(Model model){
   model.addAttribute("alldonations", donationService.getAllDonation());
   return "Ample-admin/alldonations";
     
   }
 @GetMapping("/delete/donation/{id}")
   public String AllDonations(Model model, @PathVariable Long id){
  donationService.deleteDonation(id);
   return "redirect:/onlinegiving";
     
   }
    @GetMapping("/donation/approve/{id}")
    public String approveDonation(Model model, @PathVariable Long id){
        Donation b=donationService.getDonation(id);
        if (b!= null){
        b.setApproved(1);
        b.setStatus("success");
        donationService.updateDonation(b);
        }
          model.addAttribute("alldonations", donationService.getAllDonation());
        return "redirect:/all_donations";
    
       }
  @GetMapping("/donation/unapprove/{id}")
    public String unApproveDonation(Model model, @PathVariable Long id){
        Donation b=donationService.getDonation(id);
        if (b!= null){
        b.setApproved(0);
        b.setStatus("inconclusive");
        donationService.updateDonation(b);
        }
          model.addAttribute("alldonations", donationService.getAllDonation());
        return "redirect:/all_donations";
    
       }
   
   
@GetMapping("/onlinegiving")
public String onlinedonation(Model model) {
    model.addAttribute("don",new Donation());
    return "donation2";
}
    
@PostMapping("/onlinegiving")
    public String saveOnlineDonation(@ModelAttribute Donation don, Model model){
    don.setDateDonated(new Date());
    don.setDonationMethod("PayStack");
    don.setApproved(0);
  //  don=donationService.initializeTransaction(don);
    don=donationService.addDonation(don);
    
    
    model.addAttribute("status", true);
    model.addAttribute("donation", don);
    return "payment_page";
    
    }

    
    
     @GetMapping("/onlinegiving/verifyTransaction")
    public String verifyDonation( @RequestParam String ref,@RequestParam Long id, Model model) throws Exception{
    Donation  don=    donationRepository.findById(id).orElse(null);
    don.setReference(ref);
    donationRepository.save(don);
        Donation b=donationService.verifyTransaction(ref, id);
         if(b !=null){
      model.addAttribute("don", b);
        
         }else{
            model.addAttribute("don", new Donation());
         }
          return "transaction";
       }
}
