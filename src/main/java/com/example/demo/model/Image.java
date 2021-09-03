package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String location;

    @Column
    private Integer isGallery;

   

    public Image(){
this.isGallery=0;
    }
     public Image(String title){
          this.title=title;
this.isGallery=0;
    }
    public Image(String title, String location) {
        this.title=title;
        this.location=location;
        this.isGallery=0;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getIsGallery() {
        return this.isGallery;
    }

    public void setIsGallery(Integer isGallery) {
        this.isGallery = isGallery;
    }
}
