package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //@Autowired
//  CustomSuccessHandler customSuccessHandler;

  @Autowired
  private UserDetailsService userDetailsService;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
              authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
        public void configure(WebSecurity web) throws Exception {

         web.ignoring().antMatchers("/js/**", "/css/**", "/js/**");
       
       }
 @Override
  public void configure(HttpSecurity http) throws Exception {
	 http.csrf().disable().authorizeRequests().anyRequest().permitAll();   
         
       
       
         
         
         
//         
//                 .antMatchers( "/home", "/gallery","/testimony","/register", "/blogs","/ourmission" ,"/donation","/contact","/sermon","/register").hasAnyRole("ADMIN","EXECUTIVR","WORKER","MEMBER")
//	.antMatchers("/add/*","/edit/*","/view/*", "/delete/*").hasRole("ADMIN")
//           //  .antMatchers("/customer**").hasRole("CUSTOMER")
////             .antMatchers("/customer**").hasAnyRole("CUSTOMER","ADMIN")
//           //  .antMatchers("/delivery**").hasRole("DELIVERY_AGENT")
//	.anyRequest().permitAll()
//     .and()
//     .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").defaultSuccessUrl("/blog").permitAll()
//     .and()
//     .logout().permitAll()
//     .and()
//     .csrf().disable().exceptionHandling().accessDeniedPage("/access_denied");
  }

 
   
}
