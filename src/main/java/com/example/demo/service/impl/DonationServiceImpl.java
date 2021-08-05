/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.model.Donation;
import com.example.demo.repository.DonationRepository;
import com.example.demo.service.DonationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Uduak-pc
 */
@Service
public class DonationServiceImpl implements DonationService {
    
    @Autowired
    DonationRepository donationRepository;

    @Override
    public Donation addDonation(Donation don) {
        return donationRepository.save(don); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Donation getDonation(Long id) {
       Donation don=  donationRepository.findById(id).orElse(null);
        return don; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Donation> getAllDonation() {
        List <Donation> donaList=new ArrayList<>();
        donationRepository.findAll().forEach(donaList::add); 
        return donaList;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Donation updateDonation(Donation don) {
       return donationRepository.save(don); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDonation(Long id) {
         donationRepository.deleteById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAllDonation() {
        donationRepository.deleteAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Donation> getAllApprovedDonations() {
       return donationRepository.findByApproved(1); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Donation> getAllUnapprovedDonations() {
        return donationRepository.findByApproved(0) ; //To change body of generated methods, choose Tools | Templates.
    }
    
}
