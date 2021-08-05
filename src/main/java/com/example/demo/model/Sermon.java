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
public class Sermon implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
 @Column
 private String program;
 
@Column
 private String Speaker ;
 
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
 @Temporal(javax.persistence.TemporalType.DATE)
 private Date dateAdded;
  @Column
  private int hide;

  @OneToOne
  private Image bImage;
    public Sermon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSpeaker() {
        return Speaker;
    }

    public void setSpeaker(String Speaker) {
        this.Speaker = Speaker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBibleText() {
        return bibleText;
    }

    public void setBibleText(String bibleText) {
        this.bibleText = bibleText;
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

    public void setPosterId(Long posterId) {
        this.posterId = posterId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getHide() {
        return hide;
    }

    public void setHide(int hide) {
        this.hide = hide;
    }

    public Image getBImage() {
        return bImage;
    }

    public void setBImage(Image bImage) {
        this.bImage = bImage;
    }

}
