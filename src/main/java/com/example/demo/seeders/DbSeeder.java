/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.seeders;

import com.example.demo.model.Authorities;
import com.example.demo.model.Members;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.MembersRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
//import org.apache.log4j.Logger;
import static org.hibernate.internal.HEMLogging.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Uduak-pc
 */
@Component
public class DbSeeder {
    
    
     
    private final MembersRepository membersRepository;
    private final AuthorityRepository authorityRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbSeeder(
            MembersRepository membersRepository,
            AuthorityRepository authorityRepository,
           
            JdbcTemplate jdbcTemplate) {
        this.membersRepository = membersRepository;
        this.authorityRepository= authorityRepository;
     
        this.jdbcTemplate = jdbcTemplate;
       
    }
    
    
    
    @EventListener
public void seed(ContextRefreshedEvent event) {
    seedAuthoriyTable();
    seedMembersTable();
   // seedMemberAuthoriyTable();
}


private void seedMembersTable() {
        String sql = "SELECT email FROM members U WHERE U.email = \"test@test.com\" LIMIT 1";
        List<Members> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(u == null || u.size() <= 0) {
             Members user = new Members();
             user.setFirstName("president");
             user.setLastName("admin");
             user.setEmail("test@test.com");
             user.setPassword(new BCryptPasswordEncoder().encode("test123"));
                user.setConfirmPassword(new BCryptPasswordEncoder().encode("test123"));
           
             user.setVerified(true);
             user.setCountry("Nigeria");
             user.setPhoneNumber("08036387721");
             user.setGender("Male");
             user.setDateOfBirth("19/09/2000");
             user.setMaritalStatus("single");
             user.setContactAddress("PortHarcourt");
             user.setDateRegistered(new Date());
             
           //  user.setConfirmEmail(true);
             membersRepository.save(user);
             user.getAuthority().add(authorityRepository.findByAuthorityName("ROLE_MEMBER"));
             membersRepository.save(user);
            logger("Users Seeded");
        } else {
           logger("Users Seeding Not Required");
        }
    }

private void seedAuthoriyTable() {
    String an1="ROLE_ADMIN", an2="ROLE_EXECUTIVE", an3="ROLE_WORKER",an4="ROLE_MEMBER", an5="ROLE_FINSEC";

String sql = "SELECT authority_name FROM authorities c Where c.authority_name IN(\""+an1+"\",\""+an2+"\",\""+an3+"\",\""+an4+"\",\""+an5+"\")";
        List<Authorities> a = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(a == null || a.size() <= 0) {
           Authorities auth = new Authorities("ROLE_ADMIN");
           Authorities auth1 = new Authorities("ROLE_EXECUTIVE");
            Authorities auth2 = new Authorities("ROLE_WORKER");
            Authorities auth3 = new Authorities("ROLE_MEMBER");
            Authorities auth4 = new Authorities("ROLE_FINSEC");
           authorityRepository.saveAll(Arrays.asList(auth, auth1, auth2, auth3, auth4));
                     }
}



private void seedMemberAuthoriyTable() {
   

  String sql = "SELECT email FROM members U WHERE U.email = \"test@test.com\" LIMIT 1";
        List<Members> a = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(a == null || a.size() <= 0) {
             Members user = new Members();
           user.getAuthority().add(authorityRepository.findByAuthorityName("ROLE_ADMIN"));
           membersRepository.save(user);
                     }
}
}
