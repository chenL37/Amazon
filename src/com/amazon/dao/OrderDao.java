package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.Hwua_Order;
import com.amazon.bean.Hwua_Order_Detail;
import com.amazon.bean.User;

public interface OrderDao {

	void insertDetail(int pid, float cost, int id, int count) throws SQLException;

	int getCurSeq(User u) throws SQLException;

	int insert(User u, float cost) throws SQLException;

	Hwua_Order getOrderById(int id) throws SQLException;

	List<Hwua_Order_Detail> getOrderDetailById(int id) throws SQLException;

}
