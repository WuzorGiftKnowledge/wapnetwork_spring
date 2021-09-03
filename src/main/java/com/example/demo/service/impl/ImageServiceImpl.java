/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.configurationfiles.AmazonClient;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.apache.http.entity.ContentType.IMAGE_BMP;
import static org.apache.http.entity.ContentType.IMAGE_GIF;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Uduak-pc
 */
@Service
public class ImageServiceImpl implements ImageService{

 @Autowired
private ImageRepository imageRepository;
            @Autowired 
          private AmazonClient amazonClient;
            
     @Override
    public Image addImage(Image img, MultipartFile[] files) {
          for (MultipartFile file : files) {
        if (file.isEmpty()) {
           // throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        else{ if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        
      String image_url=amazonClient.uploadFile(file);
        

         img.setLocation(image_url);
                 

        
        
        img= imageRepository.save(img);
        }
        
    }
     return img;
    }

    
     
    
    
    @Override
   public  Image getImage(Long id) {
           Image img = imageRepository.findById(id).orElse(new Image());
           return img;
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> abList= new ArrayList<>();
        imageRepository.findAll().forEach(abList::add);
        return abList; 
    }

    @Override
    public Image updateImage(Image ab) {
        return imageRepository.save(ab);
    }

    @Override
    public void deleteImage(Long id) {
     Image img =   imageRepository.findById(id).orElseThrow();
     amazonClient.deleteFileFromS3Bucket(img.getLocation());
     imageRepository.delete(img);
    }

    /*
    @Override
    public void deleteAllImage() {
       ImageRepository.deleteAll();
    }
*/
    @Override
    public List<Image> getGalleryImages() {
        List<Image> imgList= new ArrayList<>();
        imageRepository.findAll().forEach(imgList::add);
        imgList.removeIf(i -> i.getIsGallery()==0);
        return imgList;
 }

    @Override
    public Image addImage(Image img, MultipartFile file) {
    
        if (file.isEmpty()) {
           // throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        else{ if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        
      String image_url=amazonClient.uploadFile(file);
        

         img.setLocation(image_url);
                 

        
        
        img= imageRepository.save(img);
        }
        
    
     return img; //To change body of generated methods, choose Tools | Templates.
    }

  
}

    
