/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.Program;
import com.example.demo.service.ProgramService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author lynda
 */
public class ProgramController {
      @Value("${files_uploading_directory}")
    public String upload_directory;
    
    @Autowired
     private ProgramService programService;
     
     
     @GetMapping("/program")
    public String program(Model model) {
        model.addAttribute("programs",programService.getAllUpcomingPrgram());
        return "programs";
}
    
   @GetMapping("/program/add")
    public String addProgram(Model model) {
        model.addAttribute("prog", new Program());
        return "addprogram";
}
    @PostMapping("/program")
    public String saveProgram(@ModelAttribute Program prog, Model model, @RequestParam("programImage") MultipartFile programImage){
  
        
         Long l= prog.getId();
        if(l != null) {
            Program s= programService.getProgram(l);

            prog.setDatePosted(s.getDatePosted());
        prog.setUpcoming(s.getUpcoming());
        
        if(programImage.getOriginalFilename().isEmpty()){
      
       
         
        prog.setPImage(s.getPImage());
        
       programService.addProgram(prog);
       

          //continue;
    } else{
            
             String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(programImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(programImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(prog.getTitle(),  fileDownloadUri);
        prog.setPImage(image);
    }

  
       programService.addProgram(prog);
    model.addAttribute("status", true);
    
    return "redirect:/all_programs";
    
            
            
        }
        
        else{
         prog.setDatePosted(new Date());
   prog.setUpcoming(1);

    if(programImage.getOriginalFilename().isEmpty()){
        //continue;
    }else{
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(programImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(programImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(prog.getTitle(),  fileDownloadUri);
        prog.setPImage(image);
    }

  
       programService.addProgram(prog);
    model.addAttribute("status", true);
    
    return "redirect:/program/add";
    
    }
}
        

        
        
        
        
       
      @GetMapping("program/edit/{id}")
    public String addEditProgram(Model model, @PathVariable Long id) {
        
        model.addAttribute("prog", programService.getProgram(id));
        return "addprogram";
        
}
    
@GetMapping("/all_programs")
 public String ViewAllPrograms(Model model) {
        model.addAttribute("allPrograms", programService.getAllProgram());
        return "allPrograms";
}
  
  @GetMapping("program/past/{id}")
    public String pastProgram(Model model, @PathVariable Long id){
        Program b=programService.getProgram(id);
        if (b!= null){
        b.setUpcoming(0);
        programService.updateProgram(b);
        }
          model.addAttribute("allprograms", programService.getAllProgram());
        return "redirect:/all_programs";
    
       }
  @GetMapping("program/upcoming/{id}")
    public String upcomingProgram(Model model, @PathVariable Long id){
       Program b=programService.getProgram(id);
        if (b!= null){
        b.setUpcoming(1);
        programService.updateProgram(b);
        }
          model.addAttribute("allprograms", programService.getAllProgram());
        return "redirect:/all_programs";
    
       }
   
    
    
  
}
