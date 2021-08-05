/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Testimony;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface TestimonyService {
     Testimony addTestimony(Testimony tes);
    Testimony getTestimony(Long id);
    List<Testimony> getAllTestimony();
     Testimony updateTestimony(Testimony tes);
     void deleteTestimony(Long id);
      void deleteTestimony();
      List<Testimony> getAllAprovedTestimony();
       List<Testimony> getAllUnAprovedTestimony();
    
}
