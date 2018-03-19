package com.amazon.biz;

import java.util.List;

import com.amazon.bean.HwuaProduct;

public interface ProductBiz {

	List<HwuaProduct> GetHotList();

	HwuaProduct checkPro(int id);

}
