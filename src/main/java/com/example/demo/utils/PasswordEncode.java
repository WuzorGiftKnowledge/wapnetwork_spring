//package com.example.demo.utils;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//public class PasswordEncode {
//
//	public static BCryptPasswordEncoder encode(){
//		return new BCryptPasswordEncoder();
//	}
//	
//	public static boolean checkPassword(String oldPassword, String encryptedPassword) {
//		BCryptPasswordEncoder encoder = PasswordEncode.encode();
//		return encoder.matches(oldPassword, encryptedPassword);
//	}
//}
