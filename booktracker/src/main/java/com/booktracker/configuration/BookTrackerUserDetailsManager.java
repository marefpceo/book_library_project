package com.booktracker.configuration;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.booktracker.dao.UserDao;
import com.booktracker.model.BookUserDetails;
import com.booktracker.model.UserDimention;

@Service
public class BookTrackerUserDetailsManager implements UserDetailsManager {
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	
	
	public BookTrackerUserDetailsManager(UserDao userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder; 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDimention user = userDao.getUserInformation(username);
		UserDetails userDetails = new BookUserDetails(user);
		return userDetails;
	}

	@Override
	public void createUser(UserDetails user) {
		UserDimention userDimention = ((BookUserDetails)user).getUserDimention();
		String plainPassword = userDimention.getPassword();
		userDimention.setPassword(passwordEncoder.encode(plainPassword));
		userDao.registerUser(userDimention);
	}

	@Override
	public void updateUser(UserDetails user) {
		UserDimention userDimention = ((BookUserDetails)user).getUserDimention();
		String newEncryptedPassword = passwordEncoder.encode(userDimention.getPassword());
		userDimention.setPassword(newEncryptedPassword);
		userDao.updateUser(userDimention);
	}

	@Override
	public void deleteUser(String username) {
		userDao.deleteUser(username);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		
	}

	@Override
	public boolean userExists(String username) {
		return Optional.ofNullable(userDao.getUserInformation(username)).isPresent();
	}

}
