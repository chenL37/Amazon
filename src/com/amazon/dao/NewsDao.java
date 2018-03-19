package com.amazon.dao;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.News;

public interface NewsDao {

	List<News> getNewsList(int i) throws SQLException;

	News getNews(int news_id) throws SQLException;

}
