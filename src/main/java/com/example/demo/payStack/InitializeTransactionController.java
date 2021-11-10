/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.payStack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lynda
 */
@Controller
@RequestMapping("/v1")
public class InitializeTransactionController {

    @Autowired
    private InitializeTransactionService initializeTransactionService;

  
    
    
    @RequestMapping(
            path = "/initializetransaction",
            method = RequestMethod.POST)
    public String
            initializeTransaction(@ModelAttribute InitializeTransactionRequestDTO initializeTransactionRequestDTO, Model model) {
        
                
                InitializeTransactionResponseDTO initializeTransaction
                = initializeTransactionService.
                        initializeTransaction(initializeTransactionRequestDTO);
     model.addAttribute("transaction", initializeTransaction);
        return "redirect:/onlinegiving";
    }
}


