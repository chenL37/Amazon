package com.amazon.biz;

import com.amazon.bean.User;

public interface UserBiz {

	int register(User u);

	int checkName(User u);

	int checkEmail(String email);

	int LoginCheck(User loginU);

}
