/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Image;
import com.example.demo.model.Program;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ProgramRepository;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProgramService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Uduak-pc
 */
@Service
public class ProgramServiceImpl implements ProgramService {
     
    @Autowired
 private ImageService imageService;
    @Autowired
    private ProgramRepository programRepository;
     @Autowired
    private ImageRepository imageRepository;
    @Override
    public Program addProgram(Program pro, MultipartFile file) {
        
        
           
           if(file.isEmpty()){
           
           }else{
                Image image= new Image(pro.getTitle());

            image= imageService.addImage(image, file);
        
              
            
            pro.setPImage(image);
    
        }
          
      Program p= programRepository.save(pro);
      return p;
    }

    @Override
    public Program getProgram(Long id) {
           Program pro= programRepository.findById(id).orElse(null);
           return pro;
    }

    @Override
    public List<Program> getAllProgram() {
        List<Program> proList= new ArrayList<>();
        programRepository.findAll().forEach(proList::add);
        return proList;
    }

    @Override
    public Program updateProgram(Program pro) {
        return programRepository.save(pro);
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public void deleteAllProgram() {
        programRepository.deleteAll();
    }

    @Override
    public List<Program> getAllUpcomingPrgram() {
       List<Program> upprog= new ArrayList<>();
       programRepository.findByUpcoming(1).forEach(upprog::add);
        return upprog;
//To change body of generated methods, choose Tools | Templates.
    }
     
}
