/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;


import com.example.demo.model.Donation;
import com.example.demo.payStack.VerifyTransactionResponse;
import com.example.demo.repository.DonationRepository;
import com.example.demo.service.DonationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static net.bytebuddy.implementation.FixedValue.reference;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Uduak-pc
 */
@Service
public class DonationServiceImpl implements DonationService {
    RestTemplate restTemplate
            = new RestTemplate();
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

    /**
     *
     * @param ref
     * @return
     * @throws Exception
     */
    @Override
    @ResponseStatus
    public Donation verifyTransaction(String ref, Long id)throws Exception {
     //  public PaystackVerifyTransactionResponse verifyTransaction(String reference) throws Exception {
      VerifyTransactionResponse paystackresponse = null;
        Donation don=null;
          try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.paystack.co/transaction/verify/" + ref);
            request.addHeader("Content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + "sk_test_80782d8ae44aac2dc06d5e8a16320e511c4cdea2");
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(request);
            System.out.println(""+response.getStatusLine().getStatusCode());
            
            if (response.getStatusLine().getStatusCode() == 200) {
//                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    result.append(line);
                    
                     don =donationRepository.findById(id).orElse(null);
                if(don !=null){
               // don.setReference(ref);
                don.setApproved(1);
                don.setStatus("success");
                don=donationRepository.save(don);
                }

            } else {
                throw new Exception("Error Occured while connecting to paystack url");
            }
      
            ObjectMapper mapper = new ObjectMapper();

      ////    paystackresponse = mapper.readValue(result.toString(), PaystackVerifyTransactionResponse.class);

//            paystackresponse = mapper.readValue(result.toString(), VerifyTransactionResponse.class);
//
//            if (paystackresponse == null || paystackresponse.getStatus().equals("false")) {
//                throw new Exception("An error occurred while  verifying payment");
//            } else if (paystackresponse.getData().getStatus().equals("success")) {
//                don =donationRepository.findById(id).orElse(null);
//                if(don !=null){
//                don.setReference(ref);
//                don.setApproved(1);
//                don.setStatus("success");
//                don=donationRepository.save(don);
//                
//                }
//               
//               
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
          //  throw new Exception("Internal server error");
        }
        return don;
    } 
    }
    
    
    


