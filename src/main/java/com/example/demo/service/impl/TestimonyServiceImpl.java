/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Testimony;
import com.example.demo.repository.TestimonyRepository;
import com.example.demo.service.TestimonyService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class TestimonyServiceImpl implements TestimonyService{
@Autowired

TestimonyRepository testimonyRepository;
       @Override
    public Testimony addTestimony(Testimony tes) {
        return testimonyRepository.save(tes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Testimony getTestimony(Long id) {
        return testimonyRepository.findById(id).orElse(null); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Testimony> getAllTestimony() {
      List <Testimony> testList= new ArrayList<>();
      testimonyRepository.findAll().forEach(testList::add);
      return testList;
    }
    @Override
    public Testimony updateTestimony(Testimony tes) {
        return testimonyRepository.save(tes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTestimony(Long id) {
       testimonyRepository.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTestimony() {
        testimonyRepository.deleteAll(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Testimony> getAllAprovedTestimony() {
     
       return testimonyRepository.findByApproved(1);
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Testimony> getAllUnAprovedTestimony() {
       return testimonyRepository.findByApproved(0); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}