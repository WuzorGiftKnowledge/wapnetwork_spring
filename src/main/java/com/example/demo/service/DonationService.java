/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Donation;
import com.example.demo.payStack.VerifyTransactionResponse;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public interface DonationService {
    
     Donation addDonation(Donation don);
    Donation getDonation(Long id);
    List<Donation> getAllDonation();
     Donation updateDonation(Donation don);
     void deleteDonation(Long id);
      void deleteAllDonation();
      List<Donation> getAllApprovedDonations();
       List<Donation> getAllUnapprovedDonations();
        Donation  verifyTransaction(String ref, Long id)throws Exception;
}
