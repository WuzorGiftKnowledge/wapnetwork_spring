/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Blog;
import com.example.demo.model.Image;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.BlogService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
   private BlogRepository blogRepository;
   
   @Autowired
   private ImageRepository imageRepository;
   
    @Override
    public Blog addBlog(Blog bl) {
        if(bl.getBImage()!=null){
            Image image= imageRepository.save(bl.getBImage());
            bl.setBImage(image);
    
        }
     Blog bb= blogRepository.save(bl);
     return bb;
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog getBlog(Long id) {
       Blog bl=  blogRepository.findById(id).orElse(null);
        return bl; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> getAllBlog() {
        List <Blog> blaList=new ArrayList<>();
        blogRepository.findAll().forEach(blaList::add); 
        return blaList;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog updateBlog(Blog bl) {
       return blogRepository.save(bl); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBlog(Long id) {
         blogRepository.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllBlog() {
        blogRepository.deleteAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> getAllAprovedBlog() {
     
       return blogRepository.findByApproved(1);
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> getAllUnAprovedBlog() {
       return blogRepository.findByApproved(0); //To change body of generated methods, choose Tools | Templates.
    }
    
}
