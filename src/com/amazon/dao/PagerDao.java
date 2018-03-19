package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;

public interface PagerDao {

	int getCount(int hpcId) throws SQLException;

	List<HwuaProduct> getProductList(int start, int end, int hpcId) throws SQLException;

	List<HwuaProduct> getProductList(int start, int end) throws SQLException;

	int getAllCount() throws SQLException;

	List<HwuaProduct> getChildProductList(int start, int end, int hpcId) throws SQLException;

	int getLikeCount(String qname) throws SQLException;

	List<HwuaProduct> getProductList(int start, int end, String qname) throws SQLException;

	int getChildCount(int hpcId) throws SQLException;

}
