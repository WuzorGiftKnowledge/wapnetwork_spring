/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.ContactForm;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface ContactFormService {
    
    ContactForm addContactForm(ContactForm cf);
    ContactForm getContactForm(Long id);
    List<ContactForm> getAllContactForm();
     ContactForm updateContactForm(ContactForm cf);
     void deleteContactForm(Long id);
      void deleteAllContactForm();
    
}
