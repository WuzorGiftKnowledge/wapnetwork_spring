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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;


/**
 *
 * @author Uduak-pc
 */
@Entity
public class Program implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    @Size(min=2, max=100)
    private String title;
    
     @Column
    @Size(min=2, max=100)
    private String theme;
    
     @Column
    @Size(min=2, max=500)
    private String speakers;
     
      @Column
    @Size(min=2, max=500)
    private String programDate;
      
    @Column
    @Size(min=2, max=100)
    private String programTime;

        @Column
    @Size(min=2, max=100)
    private String venue;
    
            
    @Column
    @Size(min=2, max=1000)
    private String description;
    
    
    
    @OneToOne
    private Image pImage;
    
    @Column
    @Size(min=2, max=100)
    private int posterId;
    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;
    
     @Column
   private int upcoming;
     
    public Program() {
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return pImage;
    }

    public void setImage(Image pImage) {
        this.pImage = pImage;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }

    public int getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(int upcoming) {
        this.upcoming = upcoming;
    }
    
    

    public String getProgramDate() {
        return programDate;
    }

    public void setProgramDate(String programDate) {
        this.programDate = programDate;
    }

    public String getProgramTime() {
        return programTime;
    }

    public void setProgramTime(String programTime) {
        this.programTime = programTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Image getPImage() {
        return pImage;
    }

    public void setPImage(Image pImage) {
        this.pImage = pImage;
    }
    
    
    
    
}
