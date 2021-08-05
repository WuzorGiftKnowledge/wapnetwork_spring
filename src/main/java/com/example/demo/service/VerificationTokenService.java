/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.VerificationToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface VerificationTokenService {
    VerificationToken createToken(VerificationToken token);
    void deleteToken(String id);
    VerificationToken getToken(String token);
}
