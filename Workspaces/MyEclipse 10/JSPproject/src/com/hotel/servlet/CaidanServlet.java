package com.hotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotal.entity.FoodType;
import com.hotel.dao.FoodTypeDao;
import com.hotel.dao.impl.FoodTypeImpl;

public class CaidanServlet extends HttpServlet {
	private FoodTypeDao foodTypeDao=new FoodTypeImpl();
	private List<FoodType> foodTypes=null;
	
	public CaidanServlet() {
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
		String thing =request.getParameter("thing");
		if (thing.equals("findAllCaiType")) {
			findAllCaiType(request, response);
		}else if(thing.equals("updateType")){
			updateType(request, response);
		}else if(thing.equals("delete")){
			deleteType(request, response);
		}else if(thing.equals("addType")){
			addType(request, response);
		}else if(thing.equals("search")){
			searchType(request, response);
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	//查询所有菜系
	public void findAllCaiType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		foodTypes=new ArrayList<FoodType>();
		foodTypes=foodTypeDao.findFoodTypes();
		/*System.out.println("菜系数量"+foodTypes.size());*/
		request.setAttribute("foodTypes", foodTypes);
		request.getRequestDispatcher("../houtai/detail/cuisineList.jsp").forward(request, response);
	}
	//修改菜系
	public void updateType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int id=Integer.parseInt(request.getParameter("id"));
		String typeName=request.getParameter("name");
		System.out.println();
		
		foodTypeDao.updateType(id, typeName);
		findAllCaiType(request, response);
	}
	//删除菜系
	public void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		
		foodTypeDao.deleteFoodType(id);
		findAllCaiType(request, response);
	}
	//添加菜系
	public void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String typeName=request.getParameter("typeName");
		foodTypeDao.addFoodType(typeName);
		findAllCaiType(request, response);
	}
	//查询菜系
	public void searchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		foodTypes=new ArrayList<FoodType>();
		String typeName=request.getParameter("typeName");
		foodTypes=foodTypeDao.findTypeByName(typeName);
		request.setAttribute("foodTypes", foodTypes);
		request.getRequestDispatcher("../houtai/detail/cuisineList.jsp").forward(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
