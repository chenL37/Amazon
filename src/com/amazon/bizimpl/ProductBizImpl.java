package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.biz.ProductBiz;
import com.amazon.dao.ProductDao;
import com.amazon.daoimpl.ProductDaoImpl;

public class ProductBizImpl implements ProductBiz {
	@Override
	public List<HwuaProduct> GetHotList() {
		ProductDao pD = new ProductDaoImpl();
		List<HwuaProduct> list = null;
		boolean isOk = true;
		int num;
		int[] numList = new int[5];
		for (int i = 0; i < 5; i++) {
			num = (int) (Math.random() * 20);
			// 商品是否重复
			isOk = true;
			while (isOk) {
				for (int nums : numList) {
					if (num == nums) {
						num = (int) (Math.random() * 20) + 1;
						isOk = true;
						break;
					} else {
						isOk = false;
					}
				}
			}
			numList[i] = num;
		}
		try {

			list = pD.getHotPro(numList);
			System.out.println("Biz:" + list);
		} catch (SQLException e) {
			System.out.println("获取热卖失败");
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public HwuaProduct checkPro(int id) {
		HwuaProduct pro = new HwuaProduct();
		ProductDao pD = new ProductDaoImpl();
		try {
			pro = pD.checkPro(id);
		} catch (SQLException e) {
			System.out.println("获取商品失败");
			e.printStackTrace();
		}
		return pro;
	}

}
