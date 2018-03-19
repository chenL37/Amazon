package com.amazon.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.amazon.bean.HwuaProduct;
import com.amazon.dao.ProductDao;
import com.amazon.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<HwuaProduct> getHotPro(int[] numList) throws SQLException {
		List<HwuaProduct> list = new ArrayList<HwuaProduct>();
		HwuaProduct p = new HwuaProduct();
		QueryRunner run = JDBCUtils.getQueryRunner();
		for (int i = 0; i < 5; i++) {
			String sql = "	SELECT 	HP_ID,HP_NAME ,HP_DESCRIPTION ,HP_PRICE,HP_STOCK,HPC_ID,HPC_CHILD_ID,HP_FILE_NAME "
					+ "	FROM (	SELECT ROWNUM R, HP_ID,HP_NAME ,HP_DESCRIPTION ,HP_PRICE,HP_STOCK,HPC_ID,HPC_CHILD_ID,HP_FILE_NAME"
					+ "			FROM HWUA_PRODUCT)" + "	WHERE R = ?";
			p = run.query(sql, new BeanHandler<>(HwuaProduct.class), numList[i]);
			System.out.println("dao:" + numList[i] + "p:" + p);
			list.add(p);
		}
		System.out.println("dao:" + list);
		return list;
	}

	@Override
	public HwuaProduct checkPro(int id) throws SQLException {
		HwuaProduct p = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT WHERE HP_ID = ? ";
		p = run.query(sql, new BeanHandler<>(HwuaProduct.class), id);
		return p;
	}

	@Override
	public int upDateStock(int pid, int num1) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE HWUA_PRODUCT SET HP_STOCK = ? WHERE HP_ID = ?";
		return run.update(sql, num1, pid);
	}

}
