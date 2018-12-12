package com.hotel.qiantai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotal.entity.BuyCar;
import com.hotal.entity.Food;
import com.hotal.entity.orderDetail;
import com.hotel.dao.DinnerTableDao;
import com.hotel.dao.FoodDao;
import com.hotel.dao.OrderDetailDao;
import com.hotel.dao.OrdersDao;
import com.hotel.dao.impl.DinnerTableImpl;
import com.hotel.dao.impl.FoodImpl;
import com.hotel.dao.impl.OrderDetailImpl;
import com.hotel.dao.impl.OrdersImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class QOrderServlet extends HttpServlet {
	private orderDetail od=null;
	private OrderDetailDao orderDetailDao=null;
	private List<orderDetail> orderDetails=null;
	private List<orderDetail> orderDetails2=null;
	private DinnerTableDao dinnerTableDao=new DinnerTableImpl();
	private Food food=null;
	private FoodDao foodDao=new FoodImpl();
	private List<Food> foods=null;
	private OrdersDao ordersDao=new OrdersImpl();
	private List<BuyCar> buyCars=null; 
	private BuyCar buyCar=null;
	public QOrderServlet() {
		super();
	}

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
		System.out.println("foodCount"+request.getParameter("foodCount"));
		String thing=request.getParameter("thing");
		if (thing.equals("addCar")) {
			addCar(request, response);
		}else if (thing.equals("deleteOrderDetail")) {
			deleteOrderDetail(request, response);
		}else if(thing.equals("jiezhang")){
			jiezhang(request, response);
		}
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	//结账
	public void jiezhang(HttpServletRequest request, HttpServletResponse response) throws IOException{
		double totalPrice=Double.parseDouble(request.getParameter("totalPrice"));
		int orderId=(Integer)request.getSession().getAttribute("orderId");
		int tableId=(Integer)request.getSession().getAttribute("tableId");
		ordersDao.updateOrder(orderId,totalPrice);
		dinnerTableDao.updateTable(tableId, 0);
		request.getSession().removeAttribute("orderId");
		request.getSession().removeAttribute("tableId");
		response.sendRedirect("DinnerTableServlet?thing=findNoStatus");
	}
	//删除
	public void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response){
		int oid=(Integer)request.getSession().getAttribute("orderId");
		orderDetailDao.deleteOrder(oid);
	}
	//添加订单详情
	public void addCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		orderDetailDao=new OrderDetailImpl();
		
		orderDetails=new ArrayList<orderDetail>();
		orderDetails2=new ArrayList<orderDetail>();
		od=new orderDetail();
		double totalPrice=0;
		int foodCount=1;
		if (request.getParameter("foodCount")!=null) {
			foodCount=Integer.parseInt(request.getParameter("foodCount")); 
			System.out.println("为什么"+foodCount);
		}
		
		int oid=(Integer)request.getSession().getAttribute("orderId");
		int foodId=Integer.parseInt(request.getParameter("foodId"));
		System.out.println("没穿值:"+oid+"=="+foodId);
		orderDetails=orderDetailDao.findOrderDetailsByOrderId(oid,0,foodId);
		System.out.println("空的:"+orderDetails.size());
		if (orderDetails.size()>0) {
			System.out.println("为什么不输出QOrderServlet104");
			for (orderDetail od:orderDetails) {
				System.out.println("我没存哪来的"+od.getFoodCount());
				od.setFoodCount(od.getFoodCount()+foodCount);
				orderDetailDao.updateByOrderId(od);
			}
		}else{
			od.setFoodCount(foodCount);
			od.setOrderId(oid);
			od.setFood_id(foodId);
			System.out.println("不为空吧:"+(od==null));
			orderDetailDao.addOrderDetail(od);
		}

		orderDetails2=orderDetailDao.findOrderDetailsByOrderId(oid,0);
		System.out.println("不为空吧jihe:"+(orderDetails2.size()));
		for (orderDetail od:orderDetails2) {
			totalPrice=totalPrice+od.getFoodCount()*od.getFoodPrice();
		}
		request.setAttribute("totalPrice",totalPrice);
		request.setAttribute("od",orderDetails2);
		request.getRequestDispatcher("../qiantai/detail/clientOrderList.jsp").forward(request, response);
	}
	public void findOrderDetailsByOrderId(HttpServletRequest request, HttpServletResponse response){
		int oid=Integer.parseInt(request.getParameter("orderId"));
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
