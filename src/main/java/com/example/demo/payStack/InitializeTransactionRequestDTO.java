/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.payStack;

/**
 *
 * @author lynda
 */
public class InitializeTransactionRequestDTO {

    private double amount;
    private String email;
    private String reference;
    private String callback_url;
    private Integer invoice_limit;
    private Channels[] channels=Channels.values();
    private String subaccount;
    private Integer transaction_charge;
// @Enumerated(EnumType.STRING)
    private PaystackBearer paystackBearer
            = PaystackBearer.ACCOUNT;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.callback_url
                = callback_url;
    }

    public Integer getInvoice_limit() {
        return invoice_limit;
    }

    public void setInvoice_limit(Integer invoice_limit) {
        this.invoice_limit
                = invoice_limit;
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

    public Integer
            getTransaction_charge() {
        return transaction_charge;
    }

    public void setTransaction_charge(Integer transaction_charge) {
        this.transaction_charge
                = transaction_charge;
    }

    public PaystackBearer
            getPaystackBearer() {
        return paystackBearer;
    }

    public void setPaystackBearer(PaystackBearer paystackBearer) {
        this.paystackBearer
                = paystackBearer;
    }
}
