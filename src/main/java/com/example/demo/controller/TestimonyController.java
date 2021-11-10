/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Testimony;
import com.example.demo.service.TestimonyService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TestimonyController {
    @Value("${files_uploading_directory}")
    public String upload_directory;
    
     @Autowired 
             private TestimonyService testimonyService;
    
     @GetMapping("/")
    public String index(Model model){
        model.addAttribute("testimonies", testimonyService.getAllAprovedTestimony());
        return "index";
    }

    
     @GetMapping("/testimony")
    public String testimony(Model model) {
        model.addAttribute("testimonies", testimonyService.getAllAprovedTestimony());
        model.addAttribute("tes", new Testimony());
        return "testimony";
}

@GetMapping("/testimony/{id}")
public String view_single_testimony(Model model, @PathVariable int id) {
    Testimony t= testimonyService.getTestimony(Long.valueOf(id));
    if(t!=null){
        if (t.getApproved()==1){
            model.addAttribute("testimony", t);
            return "single_testimony";
        }
    }
    return "redirect:/testimony";
}
    
      @PostMapping("/testimony")
    public String saveTestitony(@ModelAttribute  Testimony tes, Model model){
        //cf.setDateAdded(new Date());
        tes.setDateAdded(new Date());
        tes.setApproved(0);
        testimonyService.addTestimony(tes);
        model.addAttribute("status", true);
    return "redirect:/testimony";
}    
    
     @GetMapping("/AllTestimony")
    public String ViewAllTestimony(Model model) {
        model.addAttribute("allTestimony", testimonyService.getAllTestimony());
        return "Ample-admin/alltestimony";
}
           @GetMapping("/unapprovedTestimony")
    public String ViewUapprovedTestimony(Model model) {
        model.addAttribute("unapprovedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "unapprovedTestimony";
}
    @GetMapping("/ApprovedTestimony")
    public String ViewApprovedTestimony(Model model) {
        model.addAttribute("approvedTestimony", testimonyService.getAllAprovedTestimony());
        return "approvedTestimony";
}
    @GetMapping("testimony/approve/{id}")
    public String approveTestimony(Model model, @PathVariable Long id){
        Testimony t=testimonyService.getTestimony(id);
        if (t!= null){
        t.setApproved(1);
        testimonyService.updateTestimony(t);
        }
          model.addAttribute("unapprovedPosts", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/unapprovedTestimony";
    
       }
    @GetMapping("testimony/unapprove/{id}")
    public String unApproveTestmony(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(0);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("approvedTestimony", testimonyService.getAllAprovedTestimony());
        return "redirect:/ApprovedTestimony";
    
       }
    @GetMapping("testimony/tes/approve/{id}")
    public String approveTestimonyAll(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(1);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("unapprovedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/AllTestimony";
    
       }
  @GetMapping("testimony/tes/unapprove/{id}")
    public String unApproveTestimonyAll(Model model, @PathVariable Long id){
        Testimony b=testimonyService.getTestimony(id);
        if (b!= null){
        b.setApproved(0);
        testimonyService.updateTestimony(b);
        }
          model.addAttribute("approvedTestimony", testimonyService.getAllUnAprovedTestimony());
        return "redirect:/AllTestimony";
    
       }
    

}
