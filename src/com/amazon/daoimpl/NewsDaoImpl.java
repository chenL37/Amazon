package com.amazon.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.News;
import com.amazon.dao.NewsDao;
import com.amazon.utils.JDBCUtils;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> getNewsList(int i) throws SQLException {
		List<News> list = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_NEWS WHERE ROWNUM < ? ORDER BY HN_ID";
		list = run.query(sql, new BeanListHandler<>(News.class), i);
		return list;
	}

	@Override
	public News getNews(int news_id) throws SQLException {
		News n = null;
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM HWUA_NEWS WHERE HN_ID = ?";
		n = run.query(sql, new BeanHandler<>(News.class), news_id);
		return n;
	}

}
