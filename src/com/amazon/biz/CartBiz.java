package com.amazon.biz;

import java.util.List;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.Hwua_Cart;
import com.amazon.bean.User;

public interface CartBiz {

	int add(int pid, int quantity, User u);

	List<Hwua_Cart> getCartList(User user);

	int changeCartCount(int id1, int count);

	int deleteCartById(int id2);

	int doBuy(String[] pIdList, String[] quantityList, User user, String cost);
	
}
