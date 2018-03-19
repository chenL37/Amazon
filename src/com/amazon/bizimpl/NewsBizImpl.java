package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.News;
import com.amazon.biz.NewsBiz;
import com.amazon.dao.NewsDao;
import com.amazon.daoimpl.NewsDaoImpl;

public class NewsBizImpl implements NewsBiz {

	News n = new News();
	NewsDao nD = new NewsDaoImpl();

	@Override
	public List<News> getNewList(int i) {
		List<News> list = null;
		try {
			list = nD.getNewsList(i);
		} catch (SQLException e) {
			System.out.println("获取新闻失败");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public News getNews(int news_id) {
		System.out.println("Biz");
		News n = null;
		try {
			n = nD.getNews(news_id);
			System.out.println("Biz" + news_id + "naaaa:" + n);
		} catch (SQLException e) {
			System.out.println("查看新闻失败");
			e.printStackTrace();
		}
		return n;
	}

}
