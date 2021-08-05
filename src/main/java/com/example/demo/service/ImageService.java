/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Image;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface ImageService {
    
    
      Image addImage(Image ab);
      Image getImage(Long id);
      List<Image> getAllImages();
      List<Image> getGalleryImages();
      Image updateImage(Image ab);
       void deleteImage(Long id);
        void deleteAllImage();
    
    
}
