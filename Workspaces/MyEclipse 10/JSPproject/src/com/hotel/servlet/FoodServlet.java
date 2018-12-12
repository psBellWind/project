package com.hotel.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.hotal.entity.Food;
import com.hotal.entity.FoodType;
import com.hotel.dao.FoodDao;
import com.hotel.dao.FoodTypeDao;
import com.hotel.dao.impl.FoodImpl;
import com.hotel.dao.impl.FoodTypeImpl;

public class FoodServlet extends HttpServlet {
	private FoodDao foodDao=new FoodImpl();
	private FoodTypeDao foodTypeDao=new FoodTypeImpl();
	private List<Food> foods=null;
	private List<FoodType> foodTypes=null;
	private Food food1=null;
	/**
	 * Constructor of the object.
	 */
	public FoodServlet() {
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
		String things=null;
		/*********文件上传组件： 处理文件上传************/
		food1=new Food();
			try {
				//创建文件上传工厂
				FileItemFactory factory=new DiskFileItemFactory();
				//创建解析器
				ServletFileUpload upload=new ServletFileUpload(factory);
				// 一、设置单个文件允许的最大的大小： 30M
				upload.setFileSizeMax(30*1024*1024);
				// 二、设置文件上传表单允许的总大小: 80M
				upload.setSizeMax(80*1024*1024);
				// 三、 设置上传表单文件名的编码
				// 相当于：request.setCharacterEncoding("UTF-8");
				upload.setHeaderEncoding("UTF-8");
				// 3. 判断： 当前表单是否为文件上传表单
				if (upload.isMultipartContent(request)) {
					//吧请求数据转换成一个FileItem对象,再用集合封装
					List<FileItem> list=upload.parseRequest(request);
					//遍历得到每一个数据
					for (FileItem item : list) {
						//判断是否为普通文本数据
						if (item.isFormField()) {
							//是普通文本数据
							String fieldName=item.getFieldName();//表单元素的名称
							String content = item.getString();// 表单元素名称， 对应的数据
							System.out.println("牛鬼蛇神"+fieldName+":"+content);
							if (fieldName.equals("cid")) {
								
								food1.setFoodType_id(Integer.parseInt(content));
							}else if (fieldName.equals("foodId")) {
								food1.setId(Integer.parseInt(content));
								
							}else if (fieldName.equals("foodName")) {
								content=new String(content.getBytes("ISO-8859-1"),"utf-8");
								food1.setFoodName(content);
							}else if (fieldName.equals("price")) {
								food1.setPrice(Double.parseDouble(content));
							}else if (fieldName.equals("mprice")) {
								food1.setMprice(Double.parseDouble(content));
							}else if (fieldName.equals("introduce")) {
								content=new String(content.getBytes("ISO-8859-1"),"utf-8");
								food1.setRemark(content);
							}else if (fieldName.equals("thing")) {
								things=content;
								
							}else {
								System.out.println("有毛病吗真实!!!!!!!!!!!!!!!!!!!!!!!!");
							}
						}// 上传文件(文件流) ----> 上传到upload目录下
						else {
							// 普通文本数据
							String fieldName = item.getFieldName();	// 表单元素名称
							String name = item.getName();			// 文件名				
							String content = item.getString();		// 表单元素名称， 对应的数据
							String type = item.getContentType();	// 文件类型
							InputStream in = item.getInputStream(); // 上传文件流
							
							/*
							 *  四、文件名重名
							 *  对于不同用户readme.txt文件，不希望覆盖！
							 *  后台处理： 给用户添加一个唯一标记!
							 */
							// a. 随机生成一个唯一标记
							String id = UUID.randomUUID().toString();
							// b. 与文件名拼接
							name = id + name;
							// 获取上传基路径
							
							String path = getServletContext().getRealPath("/upload");
							
							// 创建目标文件
							File file = new File(path,name);
							System.out.println("这路径到底是个啥");
							food1.setImg("upload"+"/"+name);
							
							// 工具类，文件上传
							item.write(file);
							item.delete();   //删除系统产生的临时文件
							
							System.out.println();
						}
					}
					if (things.equals("addFood")) {
						foodDao.addFood(food1);
						findAllFood(request, response);
					}else if(things.equals("update")){
						foodDao.updateFood(food1);
						findAllFood(request, response);}
				}else {

					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
					out.println("  <BODY>");
					
					
					String thing=request.getParameter("thing");
					if (thing.equals("findAllFood")) {
						findAllFood(request, response);
					}else if(thing.equals("findById")){
						findById(request, response);
					}else if(thing.equals("addFood")){
						addFood(request, response);
					}
					else if(thing.equals("delete")){
						deleteFood(request, response);
					}
					out.println("  </BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	//删除菜品
	public void deleteFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("foodId"));
		foodDao.deleteFood(id);
		findAllFood(request, response);
	}
	//添加菜品
	public void addFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		foodTypes=new ArrayList<FoodType>();
		foodTypes=foodTypeDao.findFoodTypes();
		
		request.setAttribute("foodTypes", foodTypes);
		request.getRequestDispatcher("../houtai/detail/saveFood.jsp").forward(request, response);
	}
	@SuppressWarnings("unchecked")
	public void findAllFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		foods=new ArrayList<Food>();
		foods=foodDao.findAllFood();
		

			if(foods.size()>1){
				Collections.sort(foods, new SortByTypeId());
				
			}
			request.setAttribute("foods", foods);
			request.getRequestDispatcher("../houtai/detail/foodList.jsp").forward(request, response);
		
	}
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		foodTypes=new ArrayList<FoodType>();
		int id = Integer.parseInt(request.getParameter("foodId"));
		food1=foodDao.findFood(id);
		foodTypes=foodTypeDao.findFoodTypes();
		
		request.setAttribute("foodTypes", foodTypes);
		request.setAttribute("food", food1);
		request.getRequestDispatcher("../houtai/detail/updateFood.jsp").forward(request, response);
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
//按菜系编号排序
class SortByTypeId implements Comparator {
    public int compare(Object o1, Object o2) {
     Food s1 = (Food) o1;
     Food s2 = (Food) o2;
    if (s1.getFoodType_id() > s2.getFoodType_id())
      return 1;
     return -1;
    }
   }
