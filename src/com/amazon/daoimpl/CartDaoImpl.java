package com.amazon.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.Hwua_Cart;
import com.amazon.bean.User;
import com.amazon.dao.CartDao;
import com.amazon.utils.JDBCUtils;

public class CartDaoImpl implements CartDao {

	@Override
	public int add(int pid, int quantity, User user) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO HWUA_CART VALUES(SEQ_HWUA_CART.NEXTVAL,?,?,?)";
		return run.update(sql, pid, quantity, user.getHU_USER_ID());
	}

	@Override
	public int getCount(int pid, int uid) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT QUANTITY FROM HWUA_CART WHERE PID = ? AND USERID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, pid);
		ps.setInt(2, uid);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public int update(int pid, int num, User user) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE HWUA_CART SET QUANTITY = ? WHERE PID = ? AND USERID = ?";
		return run.update(sql, num, pid, user.getHU_USER_ID());
	}

	@Override
	public List<Hwua_Cart> getCartList(int hu_USER_ID) throws SQLException {
		List<Hwua_Cart> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_CART WHERE USERID = ?";
		list = run.query(sql, new BeanListHandler<>(Hwua_Cart.class), hu_USER_ID);
		return list;
	}

	@Override
	public Hwua_Cart getCart(int id) throws SQLException {
		Hwua_Cart hwua_Cart = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_CART WHERE ID = ?";
		hwua_Cart = run.query(sql, new BeanHandler<>(Hwua_Cart.class), id);
		return hwua_Cart;
	}

	@Override
	public int changeCartCount(int id, int count) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE HWUA_CART SET QUANTITY = ? WHERE ID = ?";
		return run.update(sql, count, id);
	}

	@Override
	public int deleteCartById(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "DELETE FROM HWUA_CART WHERE ID = ?";
		return run.update(sql, id);
	}

	@Override
	public Hwua_Cart getCart(int pid, User user) throws SQLException {
		Hwua_Cart hwua_Cart = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_CART WHERE PID = ? AND USERID = ?";
		hwua_Cart = run.query(sql, new BeanHandler<>(Hwua_Cart.class), pid, user.getHU_USER_ID());
		return hwua_Cart;
	}

}
