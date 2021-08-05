package com.example.demo.service.impl;


import com.example.demo.model.Authorities;
import com.example.demo.model.Members;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.MembersService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MembersRepository;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.EmailService;
import com.example.demo.utils.PasswordEncode;
import java.util.HashSet;

import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Uduak-pc
 */
@Service
public class MembersServiceImpl implements MembersService{
 @Autowired
 MembersRepository membersRepository; 
 @Autowired
 EmailService emailService;
  @Autowired
 AuthorityService authorityService;
 BCryptPasswordEncoder bCryptPasswordEncoder = PasswordEncode.encode();
 
 @Autowired
   AuthorityRepository authorityRepository;

    @Override
    public Members addMembers(Members mem) {
        String encoded=new BCryptPasswordEncoder().encode(mem.getPassword());
        
        mem.setPassword(encoded);
        encoded=new BCryptPasswordEncoder().encode(mem.getConfirmPassword());
         mem.setConfirmPassword(encoded);
//        Set<Authorities> autho = new HashSet<>();
//        autho.add(authorityRepository.findByAuthorityName("MEMBER"));
//        mem.setAuthority(autho);
        emailService.sendMail(mem.getEmail(), "Hello "+mem.getFirstName(), "You are welcome to Word and Prayer Network");
      return membersRepository.save(mem); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Members getMembers(Long id) {
        return membersRepository.findById(id).orElse(null); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Members> getAllMembers() {
        List memb= new ArrayList<>();
        membersRepository.findAll().forEach(memb::add);
        return memb;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Members updateMembers(Members mem) {
       return membersRepository.save(mem);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMembers(Long id) {
       membersRepository.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllMembers() {
        membersRepository.deleteAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Members getMember(String email) {
        return membersRepository.findByEmailIgnoreCase(email);
        //To change body of generated methods, choose Tools | Templates.
    }
    

}
