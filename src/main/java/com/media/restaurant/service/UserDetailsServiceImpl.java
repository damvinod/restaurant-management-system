package com.media.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.media.restaurant.domain.Users;
import com.media.restaurant.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByUserName(username);
		
		if (user == null) {
			return null;
		}
		
		List<GrantedAuthority> auth = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole());
		
		String password = user.getPassword();
		
		return new org.springframework.security.core.userdetails.User(username, password,
				auth);
	}

}
