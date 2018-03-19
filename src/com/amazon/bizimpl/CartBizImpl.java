package com.amazon.bizimpl;

import java.sql.SQLException;
import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.Hwua_Cart;
import com.amazon.bean.User;
import com.amazon.biz.CartBiz;
import com.amazon.dao.CartDao;
import com.amazon.dao.OrderDao;
import com.amazon.dao.ProductDao;
import com.amazon.dao.UserDao;
import com.amazon.daoimpl.CartDaoImpl;
import com.amazon.daoimpl.OrderDaoImpl;
import com.amazon.daoimpl.ProductDaoImpl;
import com.amazon.daoimpl.UserDaoImpl;
import com.amazon.utils.JDBCUtils;

public class CartBizImpl implements CartBiz {
	CartDao cD = new CartDaoImpl();
	ProductDao pD = new ProductDaoImpl();
	OrderDao oD = new OrderDaoImpl();
	UserDao uD = new UserDaoImpl();

	@Override
	public int add(int pid, int quantity, User user) {
		int num = 0;
		// 获取当前购物车的总数
		try {
			num = cD.getCount(pid, user.getHU_USER_ID());
		} catch (SQLException e) {
			System.out.println("获取当前购物车商品总数失败");
			e.printStackTrace();
		}
		System.out.println("num"+num+"quantity"+quantity);
		num = num + quantity;
		System.out.println("num:"+num);
		try {
			if (num - quantity == 0) {
				num = cD.add(pid, quantity, user);
			} else {
				num = cD.update(pid, num, user);
			}
		} catch (SQLException e) {
			System.out.println("更新购物车数据失败");
			e.printStackTrace();
		}
		System.out.println("num" + num);
		return num;
	}

	@Override
	public List<Hwua_Cart> getCartList(User user) {
		ProductDao pD = new ProductDaoImpl();
		List<Hwua_Cart> list = null;
		try {
			list = cD.getCartList(user.getHU_USER_ID());
			for (Hwua_Cart hwua_Cart : list) {
				// 通过id查找商品,并绑定在购物车中
				hwua_Cart.setHwuaProduct(pD.checkPro(hwua_Cart.getPid()));
			}
		} catch (SQLException e) {
			System.out.println("获取购物车失败");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int changeCartCount(int id, int count) {
		int i = 0;
		try {
			// 通过id1获得购物车对象
			Hwua_Cart hC = new Hwua_Cart();
			hC = cD.getCart(id);
			// 获得库存
			HwuaProduct Pro;
			Pro = pD.checkPro(hC.getPid());
			// 比较库存总数
			int num = count + hC.getQuantity();
			if (num < Pro.getHp_stock()) {
				i = cD.changeCartCount(id, count);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteCartById(int id) {
		int i = 0;
		try {
			// 删除购物车
			i = cD.deleteCartById(id);
		} catch (SQLException e) {
			System.out.println("删除购物车失败");
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int doBuy(String[] pidList, String[] quantityList, User u, String cost) {
		float cost1 = Float.parseFloat(cost);
		int id = 0;
		float sumCost = 0;
		try {
			u = uD.getUserById(u.getHU_USER_ID());
			// 判断是否能进行结算
			// 遍历商品ID
			boolean b = true;
			for (int i = 0; i < pidList.length; i++) {
				// 转换成数字
				int pid = Integer.parseInt(pidList[i]);
				int count = Integer.parseInt(quantityList[i]);
				sumCost = sumCost + pD.checkPro(pid).getHp_price() * count;
				if (count > pD.checkPro(pid).getHp_stock() || count <= 0) {
					b = false;
				}
			}
			JDBCUtils.beginTransaction();
			// 表单更新~
			if (b) {
				// 生成购物车订单内容
				int insert = oD.insert(u, sumCost);
				// 生成表单成功
				if (1 == insert) {
					// 获得当前购物车订单号
					id = oD.getCurSeq(u);

					// 遍历列表插入对应的详情订单
					for (int i = 0; i < pidList.length; i++) {
						int pid = Integer.parseInt(pidList[i]);
						int count = Integer.parseInt(quantityList[i]);

						// 更新库存信息
						int count1 = pD.checkPro(pid).getHp_stock();
						count1 = count1 - count;
						pD.upDateStock(pid, count1);

						// 生成详情订单
						sumCost = pD.checkPro(pid).getHp_price() * count;
						oD.insertDetail(pid, sumCost, id, count);

						// 更新购物车信息
						Hwua_Cart cart = cD.getCart(pid, u);
						int num = cD.deleteCartById(cart.getId());

					}
				} else {
					JDBCUtils.rollbackTransaction();
				}
			} else {
				JDBCUtils.rollbackTransaction();
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

}
