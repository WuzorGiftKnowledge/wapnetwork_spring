/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;
import com.example.demo.model.About;
import com.example.demo.model.Image;
import com.example.demo.repository.AboutRepository;

import com.example.demo.service.AboutService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 *
 * @author lynda
 */
@Controller
public class AboutController {
  @Autowired
     private AboutService aboutService;
  
  @Autowired
  private AboutRepository aboutRepository;
  
    @Value("${files_uploading_directory}")
    public String upload_directory;
    
    
        @GetMapping("/ourmission")
    public String ourmission(Model model){
        model.addAttribute("About", aboutService.getAbout());
        return "ourmission";
    }
    
    
    
     @GetMapping("/about")
    public String about(Model model) {
        About a= aboutService.getAbout();
       if (a ==null){
       model.addAttribute("ab", new About());
      }else{
      model.addAttribute("ab",aboutService.getAbout());
       }
        return "Ample-admin/about";
}
    @PostMapping("/about")
   public String saveAbout(@ModelAttribute About ab, Model model, @RequestParam ("logo") MultipartFile logo ){

    ab.setDateUpdated(new Date());
    
    About abt=aboutRepository.findTopByOrderByIdDesc();
    if (abt!=null){
   ab.setId(aboutRepository.findTopByOrderByIdDesc().getId());
    }
//    if(logo.getOriginalFilename().isEmpty()){
//        //continue;
//    }else{
//        
//        
//        
//        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(logo.getOriginalFilename());
//        Path path = Paths.get(upload_directory + fileName);
//        try {
//            Files.copy(logo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/download/")
//                .path(fileName)
//                .toUriString();
//    
//        Image image= new Image("logo",  fileDownloadUri);
//        ab.setLImage(image);
//    }

  
   
   aboutService.addAbout(ab,logo);
    model.addAttribute("status", true);
    
    return "redirect:/about";
    
    }
   
       @ModelAttribute
    public void aboutSite(Model model){
       About ab= aboutService.getAbout();
       if (ab !=null){
    model.addAttribute("AboutInfo", aboutService.getAbout());
    }
    }
}
