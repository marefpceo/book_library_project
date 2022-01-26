package com.userdb.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userdb.dao.UserDao;
import com.userdb.exceptions.UserException;
import com.userdb.model.BookUserDetails;
import com.userdb.model.PublicUserInformation;
import com.userdb.model.UserDimention;
import com.userdb.model.UserPasswordReset;


@Service
public class UserServiceImpl implements UserService {

	private final UserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, UserDao userDao) {
		this.userDetailsManager = Objects.requireNonNull(userDetailsManager, "userDetailsManager");
		this.passwordEncoder = Objects.requireNonNull(passwordEncoder, "passwordEncoder");
		this.userDao = Objects.requireNonNull(userDao, "userDao");
	}

	@Override
	//@Transactional
	public PublicUserInformation registerNewUser(UserDimention userDimention) {
		if (!userDetailsManager.userExists(userDimention.getUsername())) {
			userDetailsManager.createUser(createUserDetails(userDimention));
			return createPublicUser(userDimention);
		} else {
			throw new UserException("User already exists.");
		}
	}

	@Override
	public int resetUserPassword(String userName, UserPasswordReset passwordReset) {
		UserDetails userDetails = createUserDetails(userName, passwordReset);
		UserDetails existingUserDetails = userDetailsManager.loadUserByUsername(userName);
		if (passwordEncoder.matches(userDetails.getPassword(), existingUserDetails.getPassword())) {
			userDetailsManager.updateUser(userDetails);
			return 1;
		} else {
			throw new UserException("Existing Password does not match.");
		}
	}

	@Override
	public PublicUserInformation getUserInformation(String userName) {
		UserDimention userDimention = userDao.getUserInformation(userName);
		return createPublicUser(userDimention);
	}

	private UserDetails createUserDetails(UserDimention userDimention) {
		UserDetails userDetails = new BookUserDetails(userDimention);
		return userDetails;
	}

	private UserDetails createUserDetails(String userName, UserPasswordReset passwordReset) {
		UserDimention userDimention = new UserDimention();
		userDimention.setUsername(userName);
		userDimention.setPassword(passwordReset.getNewPassword());
		UserDetails userDetails = new BookUserDetails(userDimention);
		return userDetails;
	}

	private PublicUserInformation createPublicUser(UserDimention userDetails) {
		PublicUserInformation userinformation = new PublicUserInformation();
		userinformation.setUserName(userDetails.getUsername());
		userinformation.setFirstName(userDetails.getFirstName());
		userinformation.setLastName(userDetails.getLastName());
		userinformation.setNumberOfHourReadEveryDay(userDetails.getAvgReadingHrsDaily());
		userinformation.setFavoriteGenres(userDetails.getFavoriteGenres());

		return userinformation;
	}

}
