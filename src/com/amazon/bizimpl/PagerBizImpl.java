package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.Pager;
import com.amazon.biz.PagerBiz;
import com.amazon.dao.PagerDao;
import com.amazon.daoimpl.PagerDaoImpl;

public class PagerBizImpl implements PagerBiz {

	@Override
	public List<HwuaProduct> GetProductList(String cate, String qname, int hpcId, Pager p) {
		System.out.println("Biz:cate:" + cate + "hpcId:" + hpcId + "qname:" + qname);
		List<HwuaProduct> list = null;
		int count = -1;
		PagerDao pD = new PagerDaoImpl();

		// 模糊查询的商品
		if (qname != null && !"".equals(qname)) {
			try {
				count = pD.getLikeCount(qname);
				System.out.println("count:" + count);
			} catch (SQLException e) {
				System.out.println("获取总数出错");
				e.printStackTrace();
			}
			p.setRecordCount(count);
			int start = (p.getCurrentPage() - 1) * p.PAGE_RECORD;
			int end = start + p.PAGE_RECORD;
			try {
				list = pD.getProductList(start, end, qname);
			} catch (SQLException e) {
				System.out.println("获取商品列表出错");
				e.printStackTrace();
			}

		} else if (
		// 快捷键搜索商品列表
		cate != null && !"".equals(cate)) {
			// "parent".equals(cate)
			// 父类商品列表
			if ("parent".equals(cate)) {
				try {
					count = pD.getCount(hpcId);
					System.out.println("count:" + count);
				} catch (SQLException e) {
					System.out.println("获取总数出错");
					e.printStackTrace();
				}
				p.setRecordCount(count);
				int start = (p.getCurrentPage() - 1) * p.PAGE_RECORD;
				int end = start + p.PAGE_RECORD;
				try {
					list = pD.getProductList(start, end, hpcId);
				} catch (SQLException e) {
					System.out.println("获取商品列表出错");
					e.printStackTrace();
				}
			} else
			// "child".equals(cate)
			// 子类商品列表
			if ("child".equals(cate)) {
				try {
					count = pD.getChildCount(hpcId);
					System.out.println("count:" + count);
				} catch (SQLException e) {
					System.out.println("获取总数出错");
					e.printStackTrace();
				}
				p.setRecordCount(count);
				int start = (p.getCurrentPage() - 1) * p.PAGE_RECORD;
				int end = start + p.PAGE_RECORD;
				try {
					list = pD.getChildProductList(start, end, hpcId);
				} catch (SQLException e) {
					System.out.println("获取商品列表出错");
					e.printStackTrace();
				}
			}
		} else {
			// 默认搜索商品列表
			try {
				count = pD.getAllCount();
				System.out.println("count:" + count);
			} catch (SQLException e) {
				System.out.println("获取总数出错");
				e.printStackTrace();
			}
			p.setRecordCount(count);
			int start = (p.getCurrentPage() - 1) * p.PAGE_RECORD;
			int end = start + p.PAGE_RECORD;
			System.out.println(start + "-" + end);
			try {
				list = pD.getProductList(start, end);
				System.out.println("list:" + list);
			} catch (SQLException e) {
				System.out.println("获取商品列表出错");
				e.printStackTrace();
			}

			System.out.println("Biz");
		}
		return list;
	}

}
