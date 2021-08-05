/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Sermon;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.SermonRepository;
import com.example.demo.service.SermonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class SermonServiceImpl implements SermonService{
    
    @Autowired
private SermonRepository sermonRepository;
 @Autowired
 private ImageRepository imageRepository;
     @Override
    public Sermon addSermon(Sermon ab) {
        
         Image image= imageRepository.save(ab.getBImage());
            ab.setBImage(image);
        return sermonRepository.save(ab);
    }

    @Override
    public Sermon getSermon(Long id) {
           Sermon ab = sermonRepository.findById(id).orElse(new Sermon());
           return ab;
    }

    @Override
    public List<Sermon> getAllSermons() {
        List<Sermon> abList= new ArrayList<>();
        sermonRepository.findAll().forEach(abList::add);
        return abList; 
    }

    @Override
    public Sermon updateSermon(Sermon ab) {
        return sermonRepository.save(ab);
    }

    @Override
    public void deleteSermon(Long id) {
        sermonRepository.deleteById(id);
    }

    @Override
    public void deleteAllSermon() {
        sermonRepository.deleteAll();
    }    
}


