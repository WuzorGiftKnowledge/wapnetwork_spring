///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.demo.security;
//
///**
// *
// * @author Uduak-pc
// */
//import com.example.demo.model.Authorities;
//import com.example.demo.model.Members;
//import java.util.*;
// 
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
// 
//public class MyUserDetails implements UserDetails {
// 
//    private final Members user;
//     
//    public MyUserDetails(Members user) {
//        this.user = user;
//    }
// 
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//      
//        Set<Authorities> roles = (Set<Authorities>) user.getAuthority();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//         
//        roles.forEach((role) -> {
//            authorities.add(new SimpleGrantedAuthority(role.getAuthorityName()));
//        });
//         
//        return authorities;
//    }
// 
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
// 
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
// 
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
// 
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
// 
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
// 
//    @Override
//////    public boolean isEnabled() {
//        return user.isVerified();
//    }
// 
//}