package com.amazon.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.Hwua_Order;
import com.amazon.bean.Hwua_Order_Detail;
import com.amazon.bean.User;
import com.amazon.dao.OrderDao;
import com.amazon.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public int insert(User u, float cost) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO HWUA_ORDER VALUES(SEQ_ORDER.NEXTVAL,?,?,?,SYSDATE,?,1,1)";
		return run.update(sql, u.getHU_USER_ID(), u.gethu_user_name(), u.getHU_ADDRESS(), cost);
	}

	@Override
	public int getCurSeq(User u) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT H FROM (SELECT HO_ID H,ROWNUM R FROM HWUA_ORDER WHERE HO_USER_ID = ? ORDER BY HO_CREATE_TIME DESC) WHERE ROWNUM = 1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, u.getHU_USER_ID());
		ResultSet rs = ps.executeQuery();
		int num = 0;
		if (rs.next()) {
			num = rs.getInt(1);
		}
		return num;
	}

	@Override
	public void insertDetail(int pid, float cost, int id, int count) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO HWUA_ORDER_DETAIL VALUES(SEQ_DETAIL.NEXTVAL,?,?,?,?)";
		run.update(sql, id, pid, count, cost);

	}

	@Override
	public Hwua_Order getOrderById(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_ORDER WHERE HO_ID = ?";
		return run.query(sql, new BeanHandler<>(Hwua_Order.class), id);
	}

	@Override
	public List<Hwua_Order_Detail> getOrderDetailById(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_ORDER_DETAIL WHERE HO_ID = ?";
		return run.query(sql, new BeanListHandler<>(Hwua_Order_Detail.class), id);
	}

}
