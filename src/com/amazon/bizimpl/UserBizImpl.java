package com.amazon.bizimpl;

import java.sql.SQLException;

import com.amazon.bean.User;
import com.amazon.biz.UserBiz;
import com.amazon.dao.UserDao;
import com.amazon.daoimpl.UserDaoImpl;

public class UserBizImpl implements UserBiz {
	// 实例化调用的对象
	UserDao uD = new UserDaoImpl();

	// 注册用户
	@Override
	public int register(User u) {
		int a = 0;
		try {
			a = uD.register(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public int checkName(User u) {
		int num = 0;
		try {
			num = uD.checkName(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (num != 0) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * 验证邮箱
	 */
	@Override
	public int checkEmail(String email) {
		int num = 0;
		try {
			num = uD.checkEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 登录验证
	 */
	@Override
	public int LoginCheck(User loginU) {
		loginU.setHU_USER_ID(0);
		User u = null;
		try {
			u = uD.SelectPWDByName(loginU.gethu_user_name());
		} catch (SQLException e) {
			System.out.println("验证登录出错");
			e.printStackTrace();
		}
		if (loginU.getHU_PASSWORD().equals(u.getHU_PASSWORD())) {
			loginU.setHU_USER_ID(u.getHU_USER_ID());
		}
		return loginU.getHU_USER_ID();
	}

}
