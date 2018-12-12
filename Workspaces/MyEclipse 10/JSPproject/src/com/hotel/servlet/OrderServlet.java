package com.hotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotal.entity.Orders;
import com.hotal.entity.orderDetail;
import com.hotel.dao.OrderDetailDao;
import com.hotel.dao.OrdersDao;
import com.hotel.dao.impl.OrderDetailImpl;
import com.hotel.dao.impl.OrdersImpl;

public class OrderServlet extends HttpServlet {
	private Orders order=null;
	private List<Orders> orders=null;
	private OrdersDao ordersDao=new OrdersImpl();
	private orderDetail orderDetail=null;
	private List<orderDetail> orderDetails=null;
	private OrderDetailDao orderDetailDao=new OrderDetailImpl();
	/**
	 * Constructor of the object.
	 */
	public OrderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String thing=request.getParameter("thing");
		if (thing.equals("findAllOrder")) {
			findAllOrder(request, response);
		}else if(thing.equals("jiezhang")){
			updateOrder(request, response);
		}else if(thing.equals("deleteOrder")){
			deleteOrder(request, response);
		}else if(thing.equals("findOrderDetail")){
			findOrderDetail(request, response);
		}
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	//查询订单详情
	public void findOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		orderDetails=new ArrayList<orderDetail>();
		int id=Integer.parseInt(request.getParameter("id"));
		orderDetails=orderDetailDao.findOrderDetails(id);
		request.setAttribute("orderDetails", orderDetails);
		request.getRequestDispatcher("../houtai/detail/orderDetail.jsp").forward(request, response);
	}
	//删除订单
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		ordersDao.deleteOrder(id);
		findAllOrder(request, response);
		
	}
	//修改订单
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		double totalprice=Double.parseDouble(request.getParameter("totalPrice"));
		ordersDao.updateOrder(id,totalprice);
		findAllOrder(request, response);
	}
	//查询所有订单
	public void findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		orders=new ArrayList<Orders>();
		orders=ordersDao.findAllOrders();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("../houtai/detail/orderList.jsp").forward(request, response);
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
