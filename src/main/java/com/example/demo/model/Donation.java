/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.example.demo.payStack.Channels;
import com.example.demo.payStack.PaystackBearer;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
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
    private String reference;
   
    @Column(columnDefinition = "varchar(255) default 'inconclusive'")
    private String status;
   
  @Transient
    private String callback_url;
   @Column
    private Integer invoice_limit;
   
    @Transient
    private Channels[] channels;
  @Transient
    private String subaccount;
 @Transient
    private Integer transaction_charge;
// @Enumerated(EnumType.STRING)
   @Transient
    private PaystackBearer paystackBearer
            = PaystackBearer.ACCOUNT;


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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public Integer getInvoice_limit() {
        return invoice_limit;
    }

    public void setInvoice_limit(Integer invoice_limit) {
        this.invoice_limit = invoice_limit;
    }

    public Channels[] getChannels() {
        return channels;
    }

    public void setChannels(Channels[] channels) {
        this.channels = channels;
    }

    public String getSubaccount() {
        return subaccount;
    }

    public void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    public Integer getTransaction_charge() {
        return transaction_charge;
    }

    public void setTransaction_charge(Integer transaction_charge) {
        this.transaction_charge = transaction_charge;
    }

    public PaystackBearer getPaystackBearer() {
        return paystackBearer;
    }

    public void setPaystackBearer(PaystackBearer paystackBearer) {
        this.paystackBearer = paystackBearer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
   
}
