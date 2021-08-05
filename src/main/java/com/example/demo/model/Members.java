/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author Uduak-pc
 */
@Entity
public class Members implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
    
   @Column
   @Size(min=2, max=255)
  private String firstName;
   
      @Column
   @Size(min=2, max=255)
  private String lastName;
      
  @Column
  @Email
  private String email;
  
  @Column
  @Size(min=4, max=15)
  private String phoneNumber;
  
   @Column
   @Size(min=0, max=50)
  private String Unit;
  
    @Column
   @Size(min=0, max=50)
  private String roles;
    
     @Column
   @Size(min=2, max=255)
  private String contactAddress;
     
   @Column
   @Size(min=2, max=255)
  private String country;
   
   @Column
   @Size(min=2, max=255)
  private String states;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "members_authorities",
            joinColumns = @JoinColumn(name = "members.id"),
            inverseJoinColumns = @JoinColumn(name = "authorities.id")
            )
    private Set<Authorities> authority = new HashSet<>();

//   @JoinColumn(name = "authority_id")
//	@OneToOne
//	private Authorities authority;

      @Column
   @Size(min=2, max=255)
    private String password;
      
     @Column
   @Size(min=2, max=255)
  private String dateOfBirth;
     
   @Column  
    @Temporal(javax.persistence.TemporalType.DATE)
  private Date dateRegistered;
   
    @Column
  private String gender;

   
     @Column
   @Size(min=2, max=255)
  private String maritalStatus;
     
     @Column
    private boolean verified;
     @Column
     private String ConfirmPassword;

    public Members() {
    }
    

    
     public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = role;
    }

    public Set<Authorities> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Authorities> authority) {
        this.authority = authority;
    }

   
  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
      public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
     
}
