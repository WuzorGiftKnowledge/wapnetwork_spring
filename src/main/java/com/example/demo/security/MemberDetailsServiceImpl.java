 package com.example.demo.security;



import com.example.demo.model.Members;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class MemberDetailsServiceImpl implements UserDetailsService{

	@Autowired
	MembersService membersService;
	
	@Transactional(readOnly = true)
         @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Members user = membersService.getMember(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
        
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//		Members user = membersService.getMember(name);
//	    UserBuilder builder = null;
//	    if (user != null) {
//	      
//	      builder = org.springframework.security.core.userdetails.User.withUsername(name);
//	      //builder.disabled(!user.isEnabled());
//	      builder.password(user.getPassword());
//	      String authorities = user.getAuthority().getAuthorityName();
//	      builder.authorities(authorities);
//	    } else {
//	      throw new UsernameNotFoundException("User not found.");
//	    }
//	    return builder.build();
//	}

}
