/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Sermon;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface SermonService {
     Sermon addSermon(Sermon ab);
      Sermon getSermon(Long id);
      List<Sermon> getAllSermons();
     
      Sermon updateSermon(Sermon ab);
       void deleteSermon(Long id);
        void deleteAllSermon();
    
}
