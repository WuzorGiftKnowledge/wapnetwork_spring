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

import com.example.demo.service.BlogService;
import com.example.demo.model.Blog;
import com.example.demo.model.Image;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class BlogController {
     @Value("${files_uploading_directory}")
    public String upload_directory;
     @Autowired
     private BlogService blogService;
     
      @GetMapping("/blogview")
     
    public String blogview() {
        return "blogview";
}
         @Transactional
      @GetMapping("/blog")
       public String blog(Model model) {
        model.addAttribute("blogs", blogService.getAllAprovedBlog());
        return "blogs";
}
    
         @GetMapping("/blog/add")
    public String addblog(Model model) {
        model.addAttribute("bl",new Blog());
        return "blog";
    }
        
        @PostMapping("/blog")
    public String saveBlog(@ModelAttribute Blog bl, Model model, @RequestParam("blogImage") MultipartFile blogImage){
  
        
         Long l= bl.getId();
        if(l != null) {
           Blog s= blogService.getBlog(l);

            bl.setDateupdated(s.getDateupdated());
        bl.setApproved(s.getApproved());
        
        if(blogImage.getOriginalFilename().isEmpty()){
      
       
         
        bl.setBImage(s.getBImage());
        
       blogService.addBlog(bl);
       

          //continue;
    } else{
            
              String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(bl.getTitle(),  fileDownloadUri);
        bl.setBImage(image);
    

    }

  
       blogService.addBlog(bl);
    model.addAttribute("status", true);
    
    return "redirect:/AllBlog";
    
            
            
        }
        
        else{
           bl.setDateupdated(new Date());
   bl.setApproved(0);

    if(blogImage.getOriginalFilename().isEmpty()){
        //continue;
    }else{
        String fileName =  System.currentTimeMillis()+"_"+StringUtils.cleanPath(blogImage.getOriginalFilename());
        Path path = Paths.get(upload_directory + fileName);
        try {
            Files.copy(blogImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName)
                .toUriString();
    
        Image image= new Image(bl.getTitle(),  fileDownloadUri);
        bl.setBImage(image);
    
    }

  
       blogService.addBlog(bl);
    model.addAttribute("status", true);
    
    return "redirect:/blog/add";
    
    }
}
        
       
      @GetMapping("blog/edit/{id}")
    public String addEditBlog(Model model, @PathVariable Long id) {
        
        model.addAttribute("bl", blogService.getBlog(id));
        return "blog";
        
}
    
     @GetMapping("/AllBlog")
  
     public String ViewAllBlogs(Model model) {
        model.addAttribute("allPosts", blogService.getAllBlog());
        return "allPosts";
}
         @GetMapping("/unapprovedBlog")
    public String ViewUapprovedBlogs(Model model) {
        model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "unapprovedPosts";
}
    @GetMapping("/ApprovedBlog")
    public String ViewApprovedBlogs(Model model) {
        model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "approvedPosts";
}
    @GetMapping("blog/approve/{id}")
    public String approveBlog(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(1);
        blogService.updateBlog(b);
        }
          model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "redirect:/unapprovedBlog";
    
       }
    @GetMapping("blog/unapprove/{id}")
    public String unApproveBlog(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(0);
        blogService.updateBlog(b);
        }
          model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "redirect:/ApprovedBlog";
    
       }
    @GetMapping("blog/ap/approve/{id}")
    public String approveBlogP(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(1);
        blogService.updateBlog(b);
        }
          model.addAttribute("unapprovedPosts", blogService.getAllUnAprovedBlog());
        return "redirect:/AllBlog";
    
       }
  @GetMapping("blog/ap/unapprove/{id}")
    public String unApproveBlogP(Model model, @PathVariable Long id){
        Blog b=blogService.getBlog(id);
        if (b!= null){
        b.setApproved(0);
        blogService.updateBlog(b);
        }
          model.addAttribute("approvedPosts", blogService.getAllAprovedBlog());
        return "redirect:/AllBlog";
    
       }
    
    @GetMapping("/blog/delete/{id}")
    public String blog( @PathVariable Long id) {
blogService.deleteBlog(id);
        return "redirect:/AllBlog";
    }
    
}
