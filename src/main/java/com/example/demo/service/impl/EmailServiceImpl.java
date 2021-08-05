package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
  
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(String to, String subject, String text) {
          SimpleMailMessage message = new SimpleMailMessage(); 
          message.setFrom("pst.wuzorgiftknowledge@gmail.com");
          message.setTo(to); 
          message.setSubject(subject); 
          message.setText(text);
          emailSender.send(message);
      }

    
       @Override
    public void sendMessageWithAttachment( String to, String subject, String text, String pathToAttachment) {
    
    MimeMessage message = emailSender.createMimeMessage();
     
    
    try {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("pst.wuzorgiftknowledge@gmail.com");
   
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text);
        
    FileSystemResource file  = new FileSystemResource(new File(pathToAttachment));
    helper.addAttachment("Invoice", file);
    emailSender.send(message);
  
} catch (MessagingException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
    // ...
}
}
