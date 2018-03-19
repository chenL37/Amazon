package com.amazon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.biz.UserBiz;
import com.amazon.bizimpl.UserBizImpl;

@WebServlet("/CheckEmail")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckEmailServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBiz uB = new UserBizImpl();
		String email = request.getParameter("getemail");

		int checkNum = -1;
		System.out.println("checkEmail:ser:" + email);
		checkNum = uB.checkEmail(email);
		response.getWriter().write(checkNum);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
