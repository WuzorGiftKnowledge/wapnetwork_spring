/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;
import com.example.demo.model.Program;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author Uduak-pc
 */
@Service
public interface ProgramService {
       Program addProgram(Program prog, MultipartFile file);
    Program getProgram(Long id);
    List<Program> getAllProgram();
     Program updateProgram(Program prog);
     void deleteProgram(Long id);
      void deleteAllProgram();
      List<Program> getAllUpcomingPrgram();
    
}
