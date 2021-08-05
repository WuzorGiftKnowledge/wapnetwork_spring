package com.example.demo.service;

import com.example.demo.model.Authorities;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public interface AuthorityService {
   Set<Authorities> getAllAuthorties();
}
