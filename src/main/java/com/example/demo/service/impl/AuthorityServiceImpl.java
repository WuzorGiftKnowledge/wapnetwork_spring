package com.example.demo.service.impl;


import com.example.demo.model.Authorities;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;

import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Set<Authorities> getAllAuthorties() {
        Set<Authorities> authorities= new HashSet<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }
}
