package com.amazon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.bean.HwuaProduct;
import com.amazon.biz.ProductBiz;
import com.amazon.bizimpl.ProductBizImpl;

@WebServlet("/pview")
public class PviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductBiz pB = new ProductBizImpl();
		String parameter = req.getParameter("pId");
		int id = Integer.parseInt(parameter);
		HwuaProduct pro = new HwuaProduct();
		Object obj = req.getSession().getAttribute("user");
		// 用户已经登录
		if (obj != null && !"".equals(obj)) {

		} else {
			// 用户没有登录
			pro = pB.checkPro(id);
			System.out.println("servlet:" + pro);
		}
		req.setAttribute("pro", pro);
		req.getRequestDispatcher("product_view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
