/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Uduak-pc
 */
@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    void deleteByToken(String token);
    VerificationToken findByToken(String token);
}
