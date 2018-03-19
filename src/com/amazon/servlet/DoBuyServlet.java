package com.amazon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.bean.User;
import com.amazon.biz.CartBiz;
import com.amazon.bizimpl.CartBizImpl;

@WebServlet("/doBuy")
public class DoBuyServlet extends HttpServlet {
	CartBiz cB = new CartBizImpl();
	private static final long serialVersionUID = 1L;

	public DoBuyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] pIdList = request.getParameterValues("pId");
		String[] quantityList = request.getParameterValues("number");
		if (pIdList != null && quantityList != null && !"".equals(pIdList) && !"".equals(quantityList)) {
			Object attribute6 = request.getSession().getAttribute("user");
			String parameter7 = request.getParameter("sumPrice");
			User user = (User) attribute6;
			int num3;
			num3 = cB.doBuy(pIdList, quantityList, user, parameter7);
			// 购买结果处理
			if (0 != num3) {
				response.getWriter().write("" + num3);
			} else {
				response.getWriter().write("-2");
			}
		} else {
			response.getWriter().write("-1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
