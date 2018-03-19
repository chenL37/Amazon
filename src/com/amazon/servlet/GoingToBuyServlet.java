package com.amazon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.bean.User;
import com.amazon.biz.OrderBiz;
import com.amazon.bizimpl.OrderBizImpl;

/**
 * 立即购买
 * 
 * @author admin
 *
 */
@WebServlet("/goingToBuy")
public class GoingToBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OrderBiz oB = new OrderBizImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pid1 = request.getParameter("pid");
		int pid = Integer.parseInt(pid1);
		String count1 = request.getParameter("count");
		// 判断参数是否为空
		if (pid1 != null && count1 != null && !"".equals(pid1) && !"".equals(count1)) {
			Object user1 = request.getSession().getAttribute("user");
			int count = Integer.parseInt(count1);
			// 判断货物数量是否正常
			if (count > 0) {
				User user = (User) user1;
				System.out.println("到GoingToBuyServlet:参数列表:" + pid + "" + count + "" + user);
				int num = 0;
				num = oB.doBuy(pid, count, user);
				// 判断返回值是否正常
				if (num > 0) {
					request.setAttribute("num", num);
					request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("doAction?action=pview&pId=" + pid).forward(request, response);
				}
			} else {
				request.getRequestDispatcher("doAction?action=pview&pId=" + pid).forward(request, response);
			}
		} else {
			request.getRequestDispatcher("doAction?action=pview&pId=" + pid).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
