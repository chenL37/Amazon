package com.amazon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckCodeServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getCode = request.getParameter("getcode");
		Object code = request.getSession().getAttribute("validateCode");
		String coder = (String) code;
		if (coder.equals(getCode)) {
			response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
