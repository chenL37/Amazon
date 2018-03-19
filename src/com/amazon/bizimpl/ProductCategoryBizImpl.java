package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.ProductCategory;
import com.amazon.biz.ProductCategoryBiz;
import com.amazon.dao.ProductCategoryDao;
import com.amazon.daoimpl.ProductCategoryDaoImpl;

public class ProductCategoryBizImpl implements ProductCategoryBiz {
	ProductCategoryDao pCD = new ProductCategoryDaoImpl();

	@Override
	public Map<ProductCategory, List<ProductCategory>> getMap() {
		Map<ProductCategory, List<ProductCategory>> maps = new LinkedHashMap<ProductCategory, List<ProductCategory>>();
		List<ProductCategory> keyList = null;
		List<ProductCategory> valueList = null;
		try {
			// 拿key值
			keyList = pCD.GetKey();
			for (ProductCategory productCategory : keyList) {
				// 拿value值
				valueList = pCD.GetValue(productCategory.getHpc_id());
				maps.put(productCategory, valueList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maps;
	}

	@Override
	public ProductCategory getAdd(int id) {
		ProductCategory pC = new ProductCategory();
		try {
			pC = pCD.getAdd(id);
		} catch (SQLException e) {
			System.out.println("获取位置信息失败");
			e.printStackTrace();
		}
		return pC;
	}

}
