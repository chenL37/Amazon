package com.amazon.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.amazon.bean.User;
import com.amazon.dao.UserDao;
import com.amazon.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public int register(User u) throws SQLException {
		int row;
		QueryRunner run = JDBCUtils.getQueryRunner();
		System.out.println("dao:" + u);
		String sql = "INSERT INTO HWUA_USER VALUES(SEQ_USER.NEXTVAL,?,?,?,?,?,?,?,?,1)";
		row = run.update(sql, u.gethu_user_name(), u.getHU_PASSWORD(), u.getHU_SEX(), u.getHU_BIRTHDAY(),
				u.getHU_IDENTITY_CODE(), u.getHU_EMAIL(), u.getHU_MOBILE(), u.getHU_ADDRESS());
		return row;
	}

	@Override
	public int checkName(User u) throws SQLException {
		java.sql.Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT HU_USER_ID FROM HWUA_USER WHERE HU_USER_NAME = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, u.gethu_user_name());
		ResultSet rs = ps.executeQuery();
		int row = 0;
		if (rs.next()) {
			row = rs.getInt(1);
		}
		System.out.println("dao:checkName:" + row);
		return row;
	}

	@Override
	public int checkEmail(String email) throws SQLException {
		java.sql.Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT HU_USER_ID FROM HWUA_USER WHERE HU_EMAIL = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		int row = 0;
		if (rs.next()) {
			row = rs.getInt(1);
		}
		System.out.println("dao:checkEmail:" + row);
		return row;

	}

	@Override
	public User SelectPWDByName(String name) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_USER WHERE HU_USER_NAME = ?";
		User u = run.query(sql, new BeanHandler<>(User.class), name);
		return u;
	}

	@Override
	public User getUserById(int hu_USER_ID) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_USER WHERE HU_USER_ID = ?";
		User u = run.query(sql, new BeanHandler<>(User.class), hu_USER_ID);
		return u;
	}
}
