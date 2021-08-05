/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Blog;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface BlogService {
    Blog addBlog(Blog bl);
    Blog getBlog(Long id);
    List<Blog> getAllBlog();
     Blog updateBlog(Blog bl);
     void deleteBlog(Long id);
      void deleteAllBlog();
      List<Blog> getAllAprovedBlog();
      List<Blog> getAllUnAprovedBlog();
}
