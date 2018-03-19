package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;

public interface ProductDao {

	List<HwuaProduct> getHotPro(int[] numList) throws SQLException;

	HwuaProduct checkPro(int id) throws SQLException;

	int upDateStock(int pid, int num1) throws SQLException;

}
