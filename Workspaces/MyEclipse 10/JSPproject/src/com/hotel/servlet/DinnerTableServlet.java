package com.hotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.hotal.entity.DinnerTable;
import com.hotel.dao.DinnerTableDao;
import com.hotel.dao.impl.DinnerTableImpl;

public class DinnerTableServlet extends HttpServlet {
	private DinnerTableDao dinnerTableDao=new DinnerTableImpl();
	private List<DinnerTable> findAllTable=new ArrayList<DinnerTable>();
	public DinnerTableServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	@Test
	public void test(){
		DinnerTableDao dinnerTableDao=new DinnerTableImpl();
		
		for (DinnerTable dinnerTable: dinnerTableDao.findTable(0)) {
			System.out.println(dinnerTable.getTableName()+1);
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		/*System.out.println(dinnerTables.size());*/
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String thing=request.getParameter("thing");
		
		
		//从数据库显示
		if (thing.equals("findAllTable")) {
			findAllTable(request, response);
			//按名字查询
		}else if(thing.equals("search")){
			
			findByName(request, response);
			//预定
		}else if(thing.equals("yuding")){
			yuding(request, response);
			//退桌
		}else if(thing.equals("tuizhuo")){
			tuizhuo(request, response);
		}else if(thing.equals("addOne")){
			updateAddTable(request, response);
			//删除
		}else if(thing.equals("delete")){
			deleteTal(request, response);
		}else if(thing.equals("findNoStatus")){
			findNoStatus(request, response);
		}
		
		
		/*dinnerTables=dinnerTableDao.findTable(0);
		request.setAttribute("dinnerTables", dinnerTables);
		request.getRequestDispatcher("../qiantai/index.jsp").forward(request, response);*/

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	//查询未预订的餐桌
	public void findNoStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<DinnerTable> fns=new ArrayList<DinnerTable>();
		fns=dinnerTableDao.findTable(0);
		request.setAttribute("fns", fns);
		request.getRequestDispatcher("../qiantai/index.jsp").forward(request, response);
	}
	//删除
	public void deleteTal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		
		dinnerTableDao.deletTable(id);
		findAllTable(request, response);
	}
	//添加
	public void updateAddTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String bName =request.getParameter("bName");
		dinnerTableDao.updateAdd(bName);
		findAllTable(request, response);
	}
	//预定
	public void yuding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		
		dinnerTableDao.updateTable(id, 1);
		findAllTable(request, response);
	}
	public void tuizhuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		
		dinnerTableDao.updateTable(id, 0);
		findAllTable(request, response);
	}
	//按名字查询
	public void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<DinnerTable> findByName=new ArrayList<DinnerTable>();
		String tableName=request.getParameter("keyword");
		System.out.println("名字"+tableName);
		findByName=dinnerTableDao.findByName(tableName);
		System.out.println(findByName.size());
		if(findByName.size()>0){
			request.setAttribute("findAllTable", findByName);
			request.getRequestDispatcher("../houtai/detail/boardList.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("../houtai/detail/boardList.jsp");
			return;
		}
	}
	public void findAllTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 
		
		findAllTable=dinnerTableDao.findAllTable();
		
		request.setAttribute("findAllTable", findAllTable);
		request.getRequestDispatcher("../houtai/detail/boardList.jsp").forward(request, response);
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
