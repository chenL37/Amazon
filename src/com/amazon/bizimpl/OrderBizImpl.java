package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.Hwua_Order;
import com.amazon.bean.Hwua_Order_Detail;
import com.amazon.bean.User;
import com.amazon.biz.OrderBiz;
import com.amazon.dao.CartDao;
import com.amazon.dao.OrderDao;
import com.amazon.dao.ProductDao;
import com.amazon.dao.UserDao;
import com.amazon.daoimpl.CartDaoImpl;
import com.amazon.daoimpl.OrderDaoImpl;
import com.amazon.daoimpl.ProductDaoImpl;
import com.amazon.daoimpl.UserDaoImpl;
import com.amazon.utils.JDBCUtils;

public class OrderBizImpl implements OrderBiz {
	OrderDao oD = new OrderDaoImpl();
	CartDao cD = new CartDaoImpl();
	ProductDao pD = new ProductDaoImpl();
	UserDao uD = new UserDaoImpl();

	@Override
	public int doBuy(int pid, int count, User user) {
		int num = 0;
		int num1 = 0;
		int id = 0;
		float cost;
		User u = null;
		try {
			u = uD.getUserById(user.getHU_USER_ID());
			JDBCUtils.beginTransaction();
			// 库存和count对比
			num1 = pD.checkPro(pid).getHp_stock();
			if (count <= num1) {
				// 删除库存
				num1 = num1 - count;
				num = pD.upDateStock(pid, num1);
				cost = count * pD.checkPro(pid).getHp_price();
				// 生成对应的订单
				int insert = oD.insert(u, cost);
				if (1 == insert) {
					// 得到当前的订单序号
					id = oD.getCurSeq(u);
					// 生成详情订单
					oD.insertDetail(pid, cost, id, count);
				}
				;
			}
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e2) {
				System.out.println("购物失败");
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public List<Hwua_Order_Detail> getOrderDetail(int id) {
		List<Hwua_Order_Detail> list = null;
		try {
			list = oD.getOrderDetailById(id);
			for (Hwua_Order_Detail hwua_Order_Detail : list) {
				hwua_Order_Detail.setProduct(pD.checkPro(hwua_Order_Detail.getHp_id()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Hwua_Order getOrder(int num4) {
		Hwua_Order hO = null;
		try {
			hO = oD.getOrderById(num4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hO;
	}

}
