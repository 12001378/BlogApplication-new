package com.blog.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.repository.UserRepo;


public class UserDetailsServiceImpl implements  UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.blog.entity.User user = userRepo.getUserByUsername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("cannot found user");
		}
		
		CustomUserDetailService coustomUserDetails = new CustomUserDetailService(user);
		return coustomUserDetails;
	}

}
