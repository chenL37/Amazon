package com.amazon.biz;

import java.util.List;
import java.util.Map;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.ProductCategory;

public interface ProductCategoryBiz {

	Map<ProductCategory, List<ProductCategory>> getMap();

	ProductCategory getAdd(int hpc_id);

}
