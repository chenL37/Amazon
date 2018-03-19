package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.FootMark;

public interface FootMarkDao {

	int addFootMark(int hu_USER_ID, int id) throws SQLException;

	List<FootMark> getFootById(int hu_USER_ID) throws SQLException;

}
