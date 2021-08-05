/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Uduak-pc
 */
@Entity
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
   @Size(min=2, max=255)
    @NotNull
   private String firstName;
   
   @Column
   @Size(min=2, max=255)
   private String lastName;
   
   @Column
   private double Amount;
   
   @Column
    @Temporal(javax.persistence.TemporalType.DATE)
   private Date dateDonated;
   
   @Column
   @Email
    private String email;
   
   @Column
   @Size(min=2, max= 255)
   private String description;
   
   @Column
   private String donationMethod;

   @Column
   private int approved;

  

   @Column
   private String transactionRef;
   



    public Donation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public Date getDateDonated() {
        return dateDonated;
    }

    public void setDateDonated(Date dateDonated) {
        this.dateDonated = dateDonated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDonationMethod() {
        return donationMethod;
    }

    public void setDonationMethod(String donationMethod) {
        this.donationMethod = donationMethod;
    }
   
    public int getApproved() {
        return this.approved;
    }
    
    public void setApproved(int approved) {
        this.approved = approved;
    }
    
    public String getTransactionRef() {
           return this.transactionRef;
       }
    
       public void setTransactionRef(String transactionRef) {
           this.transactionRef = transactionRef;
       }
}
