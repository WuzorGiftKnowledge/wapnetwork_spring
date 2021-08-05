package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

//    @ManyToMany(mappedBy = "authority")
//    private List<Members> members;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String authorityName;
        
        @ManyToMany(mappedBy = "authority")
    
    private Set<Members> members = new HashSet<>();

    public Authorities() {
    }

        
    public Authorities(String authorityName) {
        this.authorityName = authorityName;
    }

        
        
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Members> getMembers() {
        return members;
    }

    public void setMembers(Set<Members> members) {
        this.members = members;
    }

	

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authority_name) {
		this.authorityName = authority_name;
	}
        
        
}