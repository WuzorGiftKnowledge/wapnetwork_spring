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
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Uduak-pc
 */
@Entity
public class MailForm implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
   
 @Column
 @NotNull
 private String mail_to;
 

 @NotNull
 @Lob
  @Column(columnDefinition = "text")
 private String message;
 
 @Column
 private String subject;
 
 @Column
 @Temporal(javax.persistence.TemporalType.DATE)
 private Date dateReplied;

    public MailForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateReplied() {
        return dateReplied;
    }

    public void setDateReplied(Date dateReplied) {
        this.dateReplied = dateReplied;
    }

    public String getMail_to() {
        return mail_to;
    }

    public void setMail_to(String mail_to) {
        this.mail_to = mail_to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
 
}
