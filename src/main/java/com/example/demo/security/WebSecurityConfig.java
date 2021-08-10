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

//  @Autowired
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
      //  authProvider.setUserDetailsService(userDetailsService());
              authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
        public void configure(WebSecurity web) throws Exception {

         web.ignoring().antMatchers("/js/**", "/css/**", "/js/**");
       }

 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("**/delete**").hasRole("PRESIDENT")
//                
//                 .antMatchers("**/edit/**", "**/approve/*").hasAnyRole("ADMIN","EXECUTIVE")
//            .antMatchers("**/add*").hasAnyRole("WORKER", "EDITOR", "PRESIDENT", "EXECUTIVE")
//            .antMatchers("/program/**").hasAnyAuthority("WORKER", "EDITOR")
//            .antMatchers("/blog/*").hasAnyAuthority("WORKER", "MEMBER")
//            .antMatchers("/register/**").permitAll()
//               .antMatchers("/","blog","testimony","programs").permitAll()
             .antMatchers("/login**").permitAll()   
          .anyRequest().permitAll()
            .and()
                
                
                
                .formLogin()
     .loginPage("/login")
      .loginProcessingUrl("/login")
      .defaultSuccessUrl("/blog",true)
      .failureUrl("/login?error=true").permitAll();
}
                
                
                
//            .formLogin()
//            .loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/home").permitAll()
//            .and()
//            .logout().permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/403")
//            ;
    
  
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }
//  
//  
//  @Override
//        public void configure(WebSecurity web) throws Exception {
//
//            web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
//        }
//
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//	 http.authorizeRequests()
//                 .antMatchers( "/AllBlog", "/AllTestimony").hasAnyRole("WORKER","MEMBER", "EDITOR") //.anyRequest().hasAnyRole("ADMIN", "USER")
////	 .antMatchers("/admin/**").hasRole("ADMIN")
////             .antMatchers("/customer**").hasRole("CUSTOMER")
//////             .antMatchers("/customer**").hasAnyRole("CUSTOMER","ADMIN")
////             .antMatchers("/delivery**").hasRole("DELIVERY_AGENT")
////	 .and()
////     .authorizeRequests().antMatchers("/login**").permitAll()
//      .and()
//     .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
//                 //.successHandler(customSuccessHandler).permitAll()
//     .and()
//     .logout().permitAll()
//     .and()
//     .csrf().disable().exceptionHandling().accessDeniedPage("/access_denied");
//
//                    
//
//  }
}
