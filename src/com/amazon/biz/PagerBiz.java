package com.amazon.biz;

import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.Pager;

public interface PagerBiz {

	List<HwuaProduct> GetProductList(String cate, String qname, int hpcId, Pager p);

}
