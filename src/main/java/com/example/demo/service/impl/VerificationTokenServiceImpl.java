/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.VerificationToken;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
@Autowired
VerificationTokenRepository verificationTokenRepository;
    
    
    @Override
    public VerificationToken createToken(VerificationToken token) {
   return  verificationTokenRepository.save(token);
      
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteToken(String id) {
       verificationTokenRepository.deleteByToken(id);  
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VerificationToken getToken(String token) {
        return verificationTokenRepository.findByToken(token);//To change body of generated methods, choose Tools | Templates.
    }
    
}
