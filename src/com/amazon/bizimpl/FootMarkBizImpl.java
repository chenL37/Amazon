package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amazon.bean.FootMark;
import com.amazon.bean.HwuaProduct;
import com.amazon.biz.FootMarkBiz;
import com.amazon.dao.FootMarkDao;
import com.amazon.dao.ProductDao;
import com.amazon.daoimpl.FootMarkDaoImpl;
import com.amazon.daoimpl.ProductCategoryDaoImpl;
import com.amazon.daoimpl.ProductDaoImpl;

public class FootMarkBizImpl implements FootMarkBiz {
	FootMarkDao fMD = new FootMarkDaoImpl();

	@Override
	public int addFootMark(int hu_USER_ID, int id) {
		int num = 0;
		try {
			num = fMD.addFootMark(hu_USER_ID, id);
		} catch (SQLException e) {
			System.out.println("插入足迹失败");
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public Map<Integer, HwuaProduct> getMap(int hu_USER_ID) {
		Map<Integer, HwuaProduct> map = new LinkedHashMap<Integer, HwuaProduct>();
		List<FootMark> fM = null;
		HwuaProduct p = null;
		ProductDao pD = new ProductDaoImpl();
		try {

			// 查询记录
			fM = fMD.getFootById(hu_USER_ID);
			System.out.println("足迹fM:" + fM);
			// 查询商品
			for (int i = 0; i < (fM.size() < 3 ? fM.size() : 3); i++) {
				if (fM != null && !"".equals(fM)) {
					map.put(i, pD.checkPro(fM.get(i).getHpc_id()));
					System.out.println("Biz层获取的足迹:" + p);
				}
			}
		} catch (SQLException e) {
			System.out.println("获取足迹失败");
			e.printStackTrace();
		}

		return map;
	}

}
