/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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
public class ImageController {
    @Value("${files_uploading_directory}")
    public String upload_directory;
 
     
   
     
    @Autowired
    private ImageService imageService;
    
    
    
     @GetMapping("/gallery")
    public String gallery(Model model) {
model.addAttribute("images", imageService.getGalleryImages());
        return "photos";
}
    
    @GetMapping("/gallery/add")
public String addGalleryImage(Model model) {
    model.addAttribute("image",new Image());
    return "upload_gallery";
}
@PostMapping("/gallery")
public String saveGalleryImage(@ModelAttribute Image image, Model model, @RequestParam("images") MultipartFile[] images){

    List<Image> images2= new ArrayList<>();

    for (MultipartFile img : images) {
        if(img.getOriginalFilename().isEmpty()){
          //  continue;
        }else{
            String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(img.getOriginalFilename());
            Path path = Paths.get(upload_directory + fileName);
            try {
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/download/")
                    .path(fileName)
                    .toUriString();
            
            Image img_save= new Image(image.getTitle(),  fileDownloadUri);
            img_save.setIsGallery(1);
            imageService.addImage(img_save);
        }
        
        
    }


return "redirect:/gallery/add";

}


}
