/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.Sermon;
import com.example.demo.service.SermonService;
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
public class SermonController {
    @Autowired
    private SermonService sermonService;
        @Value("${files_uploading_directory}")
    public String upload_directory;
        
         @GetMapping("/sermons")
    public String sermons(Model model) {
        model.addAttribute("sermons", sermonService.getAllSermons());
        return "sermons";
}     
       
 @GetMapping("/sermons/add")
    public String addsermon(Model model) {
        model.addAttribute("ser",new Sermon());
        return "addsermon";
}
    
    @GetMapping("sermon/edit/{id}")
    public String addsermon(Model model, @PathVariable Long id) {
        
        model.addAttribute("ser", sermonService.getSermon(id));
        return "addsermon";
        
}
    
    
     @PostMapping("/sermon")
    public String saveSermon(@ModelAttribute Sermon ser, Model model, @RequestParam("sermonImage") MultipartFile sermonImage){ 
     Long l= ser.getId();
        if(l != null) {
            Sermon s= sermonService.getSermon(l);

            ser.setDateAdded(s.getDateAdded());
        ser.setHide(s.getHide());
        
        if(sermonImage.getOriginalFilename().isEmpty()){
      
       
         
        ser.setBImage(s.getBImage());
        
       sermonService.addSermon(ser);
       

          //continue;
    }else{
        
//          ser.setDateAdded(new Date());
//   ser.setHide(1);
        
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(sermonImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(sermonImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(ser.getTitle(),  fileDownloadUri);
        ser.setBImage(image);
       
       
    } 
        sermonService.addSermon(ser);
         return "redirect:/all_sermons";
        }else
        {
            if(sermonImage.getOriginalFilename().isEmpty()){
           
       
          //continue;
    }else{
        
          ser.setDateAdded(new Date());
           ser.setHide(1);
        
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(sermonImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(sermonImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(ser.getTitle(),  fileDownloadUri);
        ser.setBImage(image);
        
       
    }
            sermonService.addSermon(ser);
         return "redirect:/sermons/add";
                  
              
        }
   
        
  
}
    
        
   @GetMapping("/all_sermons")
   public String AllSermons(Model model){
   model.addAttribute("allsermons", sermonService.getAllSermons());
   return "allsermons";
     
   } 

   
   
   @GetMapping("sermon/hide/{id}")
    public String approveSermon(Model model, @PathVariable Long id){
        Sermon b=sermonService.getSermon(id);
        if (b!= null){
        b.setHide(0);
        sermonService.updateSermon(b);
        }
          model.addAttribute("allsermons", sermonService.getAllSermons());
        return "redirect:/all_sermons";
    
       }
  @GetMapping("sermon/unhide/{id}")
    public String unApproveSermon(Model model, @PathVariable Long id){
        Sermon b=sermonService.getSermon(id);
        if (b!= null){
        b.setHide(0);
        sermonService.updateSermon(b);
        }
          model.addAttribute("allsermons", sermonService.getAllSermons());
        return "redirect:/all_sermons";
    
       }
   

}
