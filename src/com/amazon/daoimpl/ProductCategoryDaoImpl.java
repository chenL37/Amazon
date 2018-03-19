package com.amazon.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.ProductCategory;
import com.amazon.dao.ProductCategoryDao;
import com.amazon.utils.JDBCUtils;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

	@Override
	public List<ProductCategory> GetKey() throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT_CATEGORY WHERE HPC_ID = HPC_PARENT_ID";
		List<ProductCategory> pC = run.query(sql, new BeanListHandler<>(ProductCategory.class));
		return pC;
	}

	@Override
	public List<ProductCategory> GetValue(int hpc_paren_id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT_CATEGORY WHERE HPC_PARENT_ID = ? AND HPC_ID != HPC_PARENT_ID";
		List<ProductCategory> pC = run.query(sql, new BeanListHandler<>(ProductCategory.class), hpc_paren_id);
		return pC;
	}

	@Override
	public ProductCategory getAdd(int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_PRODUCT_CATEGORY WHERE HPC_ID = ?";
		ProductCategory pC = run.query(sql, new BeanHandler<>(ProductCategory.class), id);
		return pC;
	}

}
