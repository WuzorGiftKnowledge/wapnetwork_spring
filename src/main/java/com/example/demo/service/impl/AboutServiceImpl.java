/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.configurationfiles.AmazonClient;
import com.example.demo.model.About;
import com.example.demo.model.Image;
import com.example.demo.repository.AboutRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.AboutService;
import com.example.demo.service.ImageService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.apache.http.entity.ContentType.IMAGE_BMP;
import static org.apache.http.entity.ContentType.IMAGE_GIF;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Uduak-pc
 */
@Service
public class AboutServiceImpl implements AboutService{

 @Autowired
private AboutRepository aboutRepository;
 @Autowired
 private ImageRepository imageRepository;
 @Autowired
 private ImageService imageService;
 
 @Autowired 
          private AmazonClient amazonClient;
            
     @Override
    public About addAbout(About ab, MultipartFile file) {
        

        Image image= new Image("logo");

        image= imageService.addImage(image, file);
                 
            ab.setLImage(image);
        return aboutRepository.save(ab);
    }

    @Override
    public About getAbout() {
           About ab = aboutRepository.findTopByOrderByIdDesc();
                  // .orElse(new About());
           return ab;
    }

    @Override
    public List<About> getAllAbout() {
        List<About> abList= new ArrayList<>();
        aboutRepository.findAll().forEach(abList::add);
        return abList; 
    }

    @Override
    public About updateAbout(About ab) {
        return aboutRepository.save(ab);
    }

    @Override
    public void deleteAbout(Long id) {
        aboutRepository.deleteById(id);
    }

    @Override
    public void deleteAllAbout() {
        aboutRepository.deleteAll();
    }    
}
