/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.payStack;

import static jdk.jfr.internal.instrument.JDKEvents.initialize;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author lynda
 */
@Service
public class InitializeTransactionServiceImpl
        implements
        InitializeTransactionService {

    RestTemplate restTemplate
            = new RestTemplate();

    @Override
    public InitializeTransactionResponseDTO
            initializeTransaction(InitializeTransactionRequestDTO initializeTransactionRequestDTO) {
        String url = "https://api.paystack.co / transaction/ initialize";
HttpHeaders headers
                = new HttpHeaders();
        String key = "pk_test_be8f4ea0ea4c4d8f28019a15bfab50a5635a1348";
 headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",
                "Bearer " + key);
        HttpEntity<InitializeTransactionRequestDTO> entity = new HttpEntity<InitializeTransactionRequestDTO>(initializeTransactionRequestDTO,
                headers);
        ResponseEntity<InitializeTransactionResponseDTO> response
                = restTemplate.postForEntity(url, entity,
                        InitializeTransactionResponseDTO.class);
        return response.getBody();
    }
}
