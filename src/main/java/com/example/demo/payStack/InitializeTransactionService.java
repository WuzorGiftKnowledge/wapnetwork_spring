/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.payStack;

import org.springframework.stereotype.Service;

/**
 *
 * @author lynda
 */
@Service
public interface InitializeTransactionService {
    InitializeTransactionResponseDTO
            initializeTransaction(InitializeTransactionRequestDTO initializeTransactionRequestDTO);
}
