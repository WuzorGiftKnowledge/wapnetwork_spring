/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Members;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface MembersService {
      Members addMembers(Members don);
    Members getMembers(Long id);
    List<Members> getAllMembers();
     Members updateMembers(Members don);
     void deleteMembers(Long id);
      void deleteAllMembers();
      Members getMember(String email);
}
