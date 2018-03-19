package com.amazon.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.FootMark;
import com.amazon.dao.FootMarkDao;
import com.amazon.utils.JDBCUtils;

public class FootMarkDaoImpl implements FootMarkDao {

	@Override
	public int addFootMark(int hu_USER_ID, int id) throws SQLException {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "INSERT INTO FOOTMARK VALUES(SEQ_FOOTMARK.NEXTVAL,?,?,SYSDATE)";
		return run.update(sql, id, hu_USER_ID);

	}

	@Override
	public List<FootMark> getFootById(int hu_USER_ID) throws SQLException {
		List<FootMark> n = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM (SELECT * FROM FOOTMARK WHERE USER_ID = ? ORDER BY FOOTMARK_DATE DESC) WHERE ROWNUM <3";
		n = run.query(sql, new BeanListHandler<>(FootMark.class), hu_USER_ID);
		return n;
	}

}
