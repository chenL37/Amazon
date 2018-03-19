package com.amazon.biz;

import java.util.Map;

import com.amazon.bean.HwuaProduct;

public interface FootMarkBiz {

	int addFootMark(int hu_USER_ID, int id);

	Map<Integer, HwuaProduct> getMap(int hu_USER_ID);

}
