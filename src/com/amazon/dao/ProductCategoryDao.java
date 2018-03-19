package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.ProductCategory;

public interface ProductCategoryDao {

	List<ProductCategory> GetKey() throws SQLException;

	List<ProductCategory> GetValue(int hpc_paren_id) throws SQLException;

	ProductCategory getAdd(int id) throws SQLException;


}
