package com.amazon.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazon.bean.HwuaProduct;
import com.amazon.bean.Hwua_Cart;
import com.amazon.bean.Hwua_Order;
import com.amazon.bean.Hwua_Order_Detail;
import com.amazon.bean.News;
import com.amazon.bean.Pager;
import com.amazon.bean.ProductCategory;
import com.amazon.bean.User;
import com.amazon.biz.CartBiz;
import com.amazon.biz.FootMarkBiz;
import com.amazon.biz.NewsBiz;
import com.amazon.biz.OrderBiz;
import com.amazon.biz.PagerBiz;
import com.amazon.biz.ProductBiz;
import com.amazon.biz.ProductCategoryBiz;
import com.amazon.biz.UserBiz;
import com.amazon.bizimpl.CartBizImpl;
import com.amazon.bizimpl.FootMarkBizImpl;
import com.amazon.bizimpl.NewsBizImpl;
import com.amazon.bizimpl.OrderBizImpl;
import com.amazon.bizimpl.PagerBizImpl;
import com.amazon.bizimpl.ProductBizImpl;
import com.amazon.bizimpl.ProductCategoryBizImpl;
import com.amazon.bizimpl.UserBizImpl;

@WebServlet("/doAction")
public class DoActionServlet extends HttpServlet {
	UserBiz uB = new UserBizImpl();
	User user = new User();
	ProductCategoryBiz pCB = new ProductCategoryBizImpl();
	PagerBiz pB = new PagerBizImpl();
	NewsBiz nB = new NewsBizImpl();
	CartBiz cB = new CartBizImpl();
	OrderBiz oB = new OrderBizImpl();
	private static final long serialVersionUID = 1L;

	public DoActionServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 实例调用的对象
		String action = request.getParameter("action");
		// 调用对应的方法
		switch (action) {
		case "register":
			// 成功返回1,-1存在
			// Date d = to_date('02-07-1989', 'dd-mm-yyyy')
			String veryCode = request.getParameter("veryCode");
			Object code = request.getSession().getAttribute("validateCode");
			String coder = (String) code;
			if (veryCode != null && !"".equals(veryCode) && coder.equals(veryCode)) {
				int num = -1;
				user.sethu_user_name(request.getParameter("userName"));
				user.setHU_PASSWORD(request.getParameter("passWord"));
				String rePassWord = request.getParameter("rePassWord");
				user.setHU_EMAIL(request.getParameter("email"));
				if (rePassWord.equals(user.getHU_PASSWORD()) && 0 == uB.checkEmail(user.getHU_EMAIL())) {
					String sex = request.getParameter("sex");
					user.setHU_SEX(sex);
					String date = request.getParameter("birthday");
					Date d = Date.valueOf(date);
					user.setHU_BIRTHDAY(d);
					user.setHU_IDENTITY_CODE(request.getParameter("identity"));

					user.setHU_MOBILE(request.getParameter("mobile"));
					user.setHU_ADDRESS(request.getParameter("address"));
					num = uB.register(user);
					response.getWriter().write("" + num);
				} else {
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			break;

		case "checkName":
			String userName = request.getParameter("userName");
			int checkNum = -1;
			user.sethu_user_name(userName);
			System.out.println("checkName:ser:" + userName);
			checkNum = uB.checkName(user);
			System.out.println("Biz:checkNum:" + checkNum);
			response.getWriter().write("" + checkNum);
			break;

		case "login":
			String loginName = request.getParameter("userName");
			String loginPWd = request.getParameter("passWord");
			String veryCode1 = request.getParameter("veryCode");
			Object code1 = request.getSession().getAttribute("validateCode");
			String coder1 = (String) code1;
			UserBiz uB = new UserBizImpl();
			user.sethu_user_name(loginName);
			if (veryCode1 != null && !"".equals(veryCode1) && coder1.equals(veryCode1) && 1 == uB.checkName(user)) {
				user.sethu_user_name(loginName);
				user.setHU_PASSWORD(loginPWd);
				int loginNum = 0;
				loginNum = uB.LoginCheck(user);
				if (loginNum != 0) {
					user.setHU_USER_ID(loginNum);
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("doAction?action=page").forward(request, response);
				} else {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			break;

		/**
		 * 分页显示及默认首页
		 */
		case "page":
			// 左侧分页列表内容
			Map<ProductCategory, List<ProductCategory>> map = pCB.getMap();
			// 获取值!
			Object attribute1 = request.getParameter("cate");
			String cate = (String) attribute1;
			Object attribute2 = request.getParameter("hpcId");
			String hpcId1 = (String) attribute2;
			Object attribute3 = request.getParameter("qname");
			String qname = (String) attribute3;
			Object attribute4 = request.getParameter("page");
			String page1 = (String) attribute4;
			// 转换值
			int hpcId = 0;
			if (hpcId1 != null && !"".equals(hpcId1)) {
				hpcId = Integer.parseInt(hpcId1);
			}
			int page = 1;
			if (page1 != null && !"".equals(page1)) {
				page = Integer.parseInt(page1);
			}
			// 分页获取对象
			Pager p = new Pager(page);
			List<HwuaProduct> list = null;
			list = pB.GetProductList(cate, qname, hpcId, p);
			// 获取新闻内容
			News n = new News();
			List<News> newsList = nB.getNewList(6);
			// 获取热卖商品
			ProductBiz pB1 = new ProductBizImpl();
			List<HwuaProduct> list1 = null;
			list1 = pB1.GetHotList();
			// 获取足迹对象
			Object obj1 = request.getSession().getAttribute("user");
			if (obj1 != null && !"".equals(obj1)) {
				User user1 = (User) obj1;
				System.out.println("到了这里了足迹");
				FootMarkBiz fM = new FootMarkBizImpl();
				Map<Integer, HwuaProduct> map1 = fM.getMap(user1.getHU_USER_ID());
				request.setAttribute("historyMap", map1);
			}

			// 存放对象
			request.setAttribute("qname", qname);
			request.getSession().setAttribute("hotProducts", list1);
			request.setAttribute("news", newsList);
			request.setAttribute("pList", list);
			request.setAttribute("pager", p);
			request.getSession().setAttribute("cMap", map);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;

		/**
		 * 新闻
		 */
		case "readNews":
			String attribute = request.getParameter("nid");
			int news_id = Integer.parseInt(attribute);
			News n1 = new News();
			n1.setHn_id(news_id);
			NewsBiz nB1 = new NewsBizImpl();
			n1 = nB1.getNews(news_id);
			News n2 = new News();
			List<News> newsList2 = nB.getNewList(6);
			request.setAttribute("news", newsList2);
			request.setAttribute("newsInfo", n1);
			request.getRequestDispatcher("news_view.jsp").forward(request, response);
			break;

		/**
		 * 足迹
		 */
		case "pview":
			ProductBiz pB2 = new ProductBizImpl();
			ProductCategory pC = new ProductCategory();
			ProductCategoryBiz pCB1 = new ProductCategoryBizImpl();
			String parameter = request.getParameter("pId");
			int id = Integer.parseInt(parameter);
			HwuaProduct pro = new HwuaProduct();
			Object obj = request.getSession().getAttribute("user");
			User user = (User) obj;
			// 用户已经登录
			if (obj != null && !"".equals(obj)) {
				FootMarkBiz fMB = new FootMarkBizImpl();
				if (1 == fMB.addFootMark(user.getHU_USER_ID(), id)) {
					System.out.println("添加记录成功!");
				}
			}
			// 用户没有登录
			pro = pB2.checkPro(id);
			// 获取父类位置信息
			pC = pCB1.getAdd(pro.getHpc_id());
			request.setAttribute("parent_category", pC);
			// 获取子类位置信息
			pC = pCB1.getAdd(pro.getHpc_child_id());
			request.setAttribute("child_category", pC);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher("product_view.jsp").forward(request, response);
			break;

		/**
		 * 加入购物车
		 */
		case "shoppingCart":
			String parameter2 = request.getParameter("pid");
			String parameter3 = request.getParameter("count");
			Object user1 = request.getSession().getAttribute("user");
			User u = (User) user1;
			int pid = Integer.parseInt(parameter2);
			int quantity = Integer.parseInt(parameter3);
			System.out.println("pid" + pid + "quantity" + quantity);
			if (1 == cB.add(pid, quantity, u)) {
				response.getWriter().write("" + 1);
			} else {
				response.getWriter().write("" + 0);
			}
			break;

		/**
		 * 展示购物车
		 */
		case "showCart":
			Object attribute5 = request.getSession().getAttribute("user");
			if (attribute5 != null && !"".equals(attribute5)) {
				user = (User) attribute5;
				List<Hwua_Cart> list2 = cB.getCartList(user);
				request.setAttribute("cart", list2);
			} else {
				request.setAttribute("cart", null);
			}
			request.getRequestDispatcher("shopping.jsp").forward(request, response);
			break;

		/**
		 * 更改购物车商品数量信息
		 */
		case "changeCartCount":
			String parameter4 = request.getParameter("cid");
			int id1 = Integer.parseInt(parameter4);
			String parameter5 = request.getParameter("count");
			int count = Integer.parseInt(parameter5);
			// 更改购物车信息的结果
			int num1 = cB.changeCartCount(id1, count);
			response.getWriter().write("" + num1);
			break;

		/**
		 * 购物车删除功能
		 */
		case "deleteCart":
			String parameter6 = request.getParameter("cid");
			int id2 = Integer.parseInt(parameter6);
			int num2 = cB.deleteCartById(id2);
			response.getWriter().write("" + num2);
			break;

		/**
		 * 获取订单信息
		 */
		case "getOrder":
			String str = request.getParameter("num");
			int num4 = Integer.parseInt(str);
			Hwua_Order order = null;
			List<Hwua_Order_Detail> list2 = null;
			order = oB.getOrder(num4);
			list2 = oB.getOrderDetail(num4);
			request.setAttribute("order", order);
			request.setAttribute("olist", list2);
			request.getRequestDispatcher("orders_view.jsp").forward(request, response);
			break;

		/**
		 * 退出
		 */
		case "checkOut":
			HttpSession session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("doAction?action=page").forward(request, response);
			break;

		// 页面跳转
		case "skip":
			String num = request.getParameter("num");
			System.out.println("skip:" + num);
			request.setAttribute("num", num);
			request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
			break;

		default:
			// 有误返回主页内容
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
