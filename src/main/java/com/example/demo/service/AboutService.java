/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.About;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface AboutService {
    
    About addAbout(About ab, MultipartFile file);
    About getAbout();
    List<About> getAllAbout();
     About updateAbout(About ab);
     void deleteAbout(Long id);
      void deleteAllAbout();
    
    
}
