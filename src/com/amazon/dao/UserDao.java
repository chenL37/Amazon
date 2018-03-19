package com.amazon.dao;

import java.sql.SQLException;

import com.amazon.bean.User;

public interface UserDao {

	int register(User u) throws SQLException;

	int checkName(User u) throws SQLException;

	int checkEmail(String email) throws SQLException;

	User SelectPWDByName(String hu_USER_NAME) throws SQLException;

	User getUserById(int hu_USER_ID) throws SQLException;

}
