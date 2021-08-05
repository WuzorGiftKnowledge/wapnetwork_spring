/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class ImageServiceImpl implements ImageService{

 @Autowired
private ImageRepository ImageRepository;
            
     @Override
    public Image addImage(Image ab) {
        return ImageRepository.save(ab);
    }

    @Override
    public Image getImage(Long id) {
           Image ab = ImageRepository.findById(id).orElse(new Image());
           return ab;
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> abList= new ArrayList<>();
        ImageRepository.findAll().forEach(abList::add);
        return abList; 
    }

    @Override
    public Image updateImage(Image ab) {
        return ImageRepository.save(ab);
    }

    @Override
    public void deleteImage(Long id) {
        ImageRepository.deleteById(id);
    }

    @Override
    public void deleteAllImage() {
        ImageRepository.deleteAll();
    }

    @Override
    public List<Image> getGalleryImages() {
        List<Image> abList= new ArrayList<>();
        ImageRepository.findAll().forEach(abList::add);
        abList.removeIf(i -> i.getIsGallery()==0);
        return abList;
 }

  
}
