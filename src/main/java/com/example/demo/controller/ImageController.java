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
import org.springframework.web.bind.annotation.DeleteMapping;
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

  
         
           image.setIsGallery(1);
            imageService.addImage(image, images);
    


return "redirect:/gallery/add";

}

@GetMapping("delete/gallery/{id}")
public String deleteGalleryImage(Model model , @PathVariable Long id) {
   imageService.deleteImage(id);
    model.addAttribute("image","Image Deleted Successfully");
    return "allgallery";
}
 @GetMapping("/all_gallery_images")
   public String AllContact(Model model){
   model.addAttribute("allgallery", imageService.getAllImages());
   return "Ample-admin/allgallery";
     
   }
   
   


}
