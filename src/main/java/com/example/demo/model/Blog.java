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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author Uduak-pc
 */
@Entity
public class Blog implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 
 @Column
 @Size(min=2, max=1000)
 private String title;
 
  @Column
 @Size(min=2, max=1000)
 private String bibleText;
  
  @Lob
 @Column(columnDefinition="text")
 private String message;
  @Column
 private Long posterId;
  
  @Column
 private byte picture;
  
  @Column
 @Temporal(javax.persistence.TemporalType.DATE)
 private Date dateupdated;
  @Column
  private int approved;

  @OneToOne
  private Image bImage;


 

   

    public Blog() {
    }
 public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getPosterId() {
        return posterId;
    }

    public void setPosterid(Long posterId) {
        this.posterId = posterId;
    }

    public byte getPicture() {
        return picture;
    }

    public void setPicture(byte picture) {
        this.picture = picture;
    }

    public Date getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Date dateupdated) {
        this.dateupdated = dateupdated;
    }

    public String getBibleText() {
        return bibleText;
    }

    public void setBibleText(String bibleText) {
        this.bibleText = bibleText;
    }
    public Image getBImage() {
        return this.bImage;
    }
  
    public void setBImage(Image bImage) {
        this.bImage = bImage;
    }
}
