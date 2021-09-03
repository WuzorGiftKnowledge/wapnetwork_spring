/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Image;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface ImageService {
    
    
      Image addImage(Image img, MultipartFile[] file);
       Image addImage(Image img, MultipartFile file);
      Image getImage(Long id);
      List<Image> getAllImages();
      List<Image> getGalleryImages();
      Image updateImage(Image img);
       void deleteImage(Long id);
 //       void deleteAllImage();
    
    
}
