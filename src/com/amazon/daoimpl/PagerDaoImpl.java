package com.amazon.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.HwuaProduct;
import com.amazon.dao.PagerDao;
import com.amazon.utils.JDBCUtils;

public class PagerDaoImpl implements PagerDao {

	@Override
	public int getChildCount(int hpcId) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HPC_CHILD_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, hpcId);
		ResultSet rs = ps.executeQuery();
		int count = -1;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public int getCount(int hpcId) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HPC_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, hpcId);
		ResultSet rs = ps.executeQuery();
		int count = -1;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public List<HwuaProduct> getProductList(int start, int end, int hpcId) throws SQLException {
		List<HwuaProduct> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT *" + "FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_ID = ?)T "
				+ "WHERE T.R>? AND T.R<=?";

		list = run.query(sql, new BeanListHandler<>(HwuaProduct.class), hpcId, start, end);
		return list;
	}

	@Override
	public List<HwuaProduct> getProductList(int start, int end) throws SQLException {
		List<HwuaProduct> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT *" + "FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT )T "
				+ "WHERE T.R>? AND T.R<=?";

		list = run.query(sql, new BeanListHandler<>(HwuaProduct.class), start, end);
		return list;
	}

	@Override
	public int getAllCount() throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int count = -1;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public List<HwuaProduct> getChildProductList(int start, int end, int hpcId) throws SQLException {
		List<HwuaProduct> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT *" + "FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HPC_CHILD_ID = ?)T "
				+ "WHERE T.R>? AND T.R<=?";

		list = run.query(sql, new BeanListHandler<>(HwuaProduct.class), hpcId, start, end);
		return list;
	}

	@Override
	public int getLikeCount(String qname) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sql = "SELECT COUNT(HP_ID) FROM HWUA_PRODUCT WHERE HP_NAME LIKE ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String name = "%" + qname + "%";
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		int count = -1;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		return count;
	}

	@Override
	public List<HwuaProduct> getProductList(int start, int end, String qname) throws SQLException {
		List<HwuaProduct> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT *" + "FROM(SELECT ROWNUM R,HWUA_PRODUCT.* FROM HWUA_PRODUCT WHERE HP_NAME LIKE ?)T "
				+ "WHERE T.R>? AND T.R<=?";
		String name = "%" + qname + "%";
		list = run.query(sql, new BeanListHandler<>(HwuaProduct.class), name, start, end);
		System.out.println("like:" + list);
		return list;
	}

}
