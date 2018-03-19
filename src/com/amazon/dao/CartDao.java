package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.Hwua_Cart;
import com.amazon.bean.User;

public interface CartDao {

	int add(int pid, int quantity, User user) throws SQLException;

	int getCount(int pid, int i) throws SQLException;

	int update(int pid, int num, User user) throws SQLException;

	List<Hwua_Cart> getCartList(int hu_USER_ID) throws SQLException;

	Hwua_Cart getCart(int id) throws SQLException;

	int changeCartCount(int id, int count) throws SQLException;

	int deleteCartById(int id) throws SQLException;

	Hwua_Cart getCart(int pid, User user) throws SQLException;
	

}
