package com.userdb.service;

import com.userdb.model.PublicUserInformation;
import com.userdb.model.UserDimention;
import com.userdb.model.UserPasswordReset;

public interface UserService {

	public PublicUserInformation registerNewUser(UserDimention userDimention);

	public int resetUserPassword(String userName, UserPasswordReset passwordReset);

	public PublicUserInformation getUserInformation(String userName);

}
