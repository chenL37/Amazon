package com.amazon.biz;

import java.util.List;

import com.amazon.bean.News;

public interface NewsBiz {

	List<News> getNewList(int i);

	News getNews(int news_id);

}
