/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.MailForm;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface MailFormService {
   MailForm addMailForm(MailForm ab);
      MailForm getMailForm(Long id);
      List<MailForm> getAllMailForms();
    
      MailForm updateMailForm(MailForm ab);
       void deleteMailForm(Long id);
        void deleteAllMailForm();
      
}
