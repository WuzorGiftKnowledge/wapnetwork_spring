/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author Uduak-pc
 */
@Entity
public class VerificationToken implements Serializable {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    
    private String token;
  
    @OneToOne(targetEntity = Members.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Members member;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiryDate;

    public VerificationToken() {
        expiryDate=calculateExpiryDate(EXPIRATION);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
   
    
    // standard constructors, getters and setters
}
