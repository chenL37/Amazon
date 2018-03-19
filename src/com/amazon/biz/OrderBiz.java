package com.amazon.biz;

import java.util.List;

import com.amazon.bean.Hwua_Order;
import com.amazon.bean.Hwua_Order_Detail;
import com.amazon.bean.User;

public interface OrderBiz {

	int doBuy(int pid, int count, User user);

	List<Hwua_Order_Detail> getOrderDetail( int num4);

	Hwua_Order getOrder(int num4);

}
