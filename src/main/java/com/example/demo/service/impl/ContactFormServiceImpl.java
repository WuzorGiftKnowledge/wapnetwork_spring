/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.ContactForm;
import com.example.demo.repository.ContactFormRepository;
import com.example.demo.service.ContactFormService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class ContactFormServiceImpl implements ContactFormService{

    @Autowired
    private ContactFormRepository contactFormRepository;
    @Override
    public ContactForm addContactForm(ContactForm cf) {
        return contactFormRepository.save(cf);
    }

    @Override
    public ContactForm getContactForm(Long id) {
           ContactForm cf= contactFormRepository.findById(id).orElse(null);
           return cf;
    }

    @Override
    public List<ContactForm> getAllContactForm() {
        List<ContactForm> cfList= new ArrayList<>();
        contactFormRepository.findAll().forEach(cfList::add);
        return cfList;
    }

    @Override
    public ContactForm updateContactForm(ContactForm cf) {
        return contactFormRepository.save(cf);
    }

    @Override
    public void deleteContactForm(Long id) {
        contactFormRepository.deleteById(id);
    }

    @Override
    public void deleteAllContactForm() {
        contactFormRepository.deleteAll();
    }
    
}
