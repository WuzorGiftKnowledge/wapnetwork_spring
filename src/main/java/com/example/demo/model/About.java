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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Uduak-pc
 **/
  @Entity
public class About implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Column
    @Size(min=0, max=500)
    private String vision;
    
     @Column
    @Size(min=0, max=500)
    private String mission;
     
      @Column
    @Size(min=0, max=500)
    private String Description;
     
      @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateUpdated;
    
    @Column
    private String posterID;

    @Column
    private String email;

    @Column
    private String phonenumber;

    @Column
    private String facebook;

    @Column
    private String twitter;

    @Column
    private String linkedin;

    @Column
    private String youtube;

    @Column
    private String address;
     @OneToOne
     private Image lImage;
    
    public About() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getLImage() {
        return lImage;
    }

    public void setLImage(Image lImage) {
        this.lImage = lImage;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return this.linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    
    public String getYoutube() {
        return this.youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//     @Transient
//    public String getPhotosImagePath() {
//        if (logo == null || id == null) return null;
//         
//        return "uploadingDir" + id + "/" + logo;
//    }
}
