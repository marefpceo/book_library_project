package com.userdb.dao;

import java.util.List;

import com.userdb.model.UserDimention;

public interface UserDao {
	
public int getUserCount();

public int registerUser(UserDimention userInformation);

public UserDimention getUserInformation(String userName);

public int deleteUser(String userId);

public int updateUser(UserDimention user);

}
