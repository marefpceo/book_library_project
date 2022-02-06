package com.booktracker.dao;

import java.util.List;

import com.booktracker.model.UserDimention;

public interface UserDao {
	
public int getUserCount();

public int registerUser(UserDimention userInformation);

public UserDimention getUserInformation(String userName);

public int deleteUser(String userId);

public int updateUser(UserDimention user);

}
