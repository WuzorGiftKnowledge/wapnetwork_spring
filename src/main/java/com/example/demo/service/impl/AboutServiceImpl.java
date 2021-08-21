/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.About;
import com.example.demo.model.Image;
import com.example.demo.repository.AboutRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.AboutService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     @Override
    public About addAbout(About ab) {
        
         Image image= imageRepository.save(ab.getLImage());
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
