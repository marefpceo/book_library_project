package com.booktracker.service;

import com.booktracker.model.PublicUserInformation;
import com.booktracker.model.UserDimention;
import com.booktracker.model.UserPasswordReset;

public interface UserService {

	public PublicUserInformation registerNewUser(UserDimention userDimention);

	public int resetUserPassword(String userName, UserPasswordReset passwordReset);

	public PublicUserInformation getUserInformation(String userName);

}
