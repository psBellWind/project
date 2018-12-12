package com.hotel.qiantai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotal.entity.Food;
import com.hotal.entity.FoodType;
import com.hotal.entity.Orders;
import com.hotel.dao.DinnerTableDao;
import com.hotel.dao.FoodDao;
import com.hotel.dao.FoodTypeDao;
import com.hotel.dao.OrdersDao;
import com.hotel.dao.impl.DinnerTableImpl;
import com.hotel.dao.impl.FoodImpl;
import com.hotel.dao.impl.FoodTypeImpl;
import com.hotel.dao.impl.OrdersImpl;

public class QFoodServlet extends HttpServlet {
	private Map<Integer, HttpSession> map=new HashMap<Integer, HttpSession>();
	private FoodDao foodDao=new FoodImpl();
	private FoodTypeDao foodTypeDao=new FoodTypeImpl();
	private List<Food> foods=null;
	private List<FoodType> foodTypes=null;
	private Food food=null;
	private OrdersDao ordersDao=new OrdersImpl();
	private Orders order=null;
	private static int orderId=10000; 
	Date date=new Date();
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public QFoodServlet() {
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
		
		String thing=request.getParameter("thing");
		if(thing.equals("findByTypeName")){
			findByTypeName(request, response);
		}else if (thing.equals("selectFood")) {
			selectFood(request, response);
		}else if(thing.equals("findFoodById")){
			findFoodById(request, response);
		}else if(thing.equals("findFoodByIdCar")){
			findFoodByIdCar(request, response);
		}
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	//通过id查询菜品
		public void findFoodByIdCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			food =new Food();
			
			int id=Integer.parseInt(request.getParameter("foodId"));
			
			food=foodDao.findFood(id);
			
			request.setAttribute("food", food);
			
				request.getRequestDispatcher("../qiantai/detail/clientCart.jsp").forward(request, response);
			
		}
	//通过id查询菜品
	public void findFoodById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		food =new Food();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		food=foodDao.findFood(id);
		
		request.setAttribute("food", food);
		
			request.getRequestDispatcher("../qiantai/detail/caixiangxi.jsp").forward(request, response);
		
	}
	//通过菜名查询
	public void selectFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		foods=new ArrayList<Food>();
		String foodName=request.getParameter("foodName");
		foods=foodDao.selectFood(foodName);
		request.setAttribute("foods", foods);
		
		request.getRequestDispatcher("../qiantai/detail/caidan.jsp").forward(request, response);
	}
	//通过菜系查询
		public void findByTypeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			String orderString=request.getParameter("zhiling");
			
			if (orderString!=null) {//点击餐桌时预定
				order=new Orders();	
				
				order.setId(orderId);
				order.setTable_id(Integer.parseInt(request.getParameter("tableId")));
				order.setTable_name(request.getParameter("tableName"));
				String orderDate=sf.format(date);
				order.setOrderDate(orderDate);
				ordersDao.addOrder(order);
				request.getSession().setAttribute("orderId", orderId);
				orderId++;
			}
			foods=new ArrayList<Food>();
			String typeName=request.getParameter("typeName");
			String tableStatus=request.getParameter("tableStatus");
			if (tableStatus!=null) {
				DinnerTableDao dinnerTableDao=new DinnerTableImpl();
				dinnerTableDao.updateTable(Integer.parseInt(request.getParameter("tableId")), Integer.parseInt(tableStatus));
			}
			if (request.getParameter("showPage")!=null) {
				int showPage=Integer.parseInt(request.getParameter("showPage"));
				request.setAttribute("showPage",showPage);
			}
			foods=foodDao.findFood(typeName);
			if (foods.size()>0) {
				System.out.println("测试2"+foods.size());
				request.setAttribute("foods", foods);
				request.getSession().setAttribute("tableId", request.getParameter("tableId"));
				System.out.println("session存值"+request.getParameter("tableId"));
				request.getRequestDispatcher("../qiantai/detail/caidan.jsp").forward(request, response);
				
			}else {
				  
			}
			
			
		}
	public void init() throws ServletException {
		// Put your code here
	}

}
