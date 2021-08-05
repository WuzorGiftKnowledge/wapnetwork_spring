/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.MailForm;
import com.example.demo.repository.MailFormRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.MailFormService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class MailFormServiceImpl implements MailFormService{
     @Autowired
    MailFormRepository mailFormRepository;
    @Autowired
    EmailService emailService;
    @Override
    public MailForm addMailForm(MailForm don) {
        emailService.sendMail(don.getMail_to()+"", don.getSubject()+"", don.getMessage()+"");
              
        
        return mailFormRepository.save(don); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MailForm getMailForm(Long id) {
       MailForm don=  mailFormRepository.findById(id).orElse(null);
        return don; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MailForm> getAllMailForms() {
        List <MailForm> donaList=new ArrayList<>();
        mailFormRepository.findAll().forEach(donaList::add); 
        return donaList;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MailForm updateMailForm(MailForm don) {
       return mailFormRepository.save(don); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMailForm(Long id) {
         mailFormRepository.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllMailForm() {
        mailFormRepository.deleteAll(); //To change body of generated methods, choose Tools | Templates.
    }

   

   
}
