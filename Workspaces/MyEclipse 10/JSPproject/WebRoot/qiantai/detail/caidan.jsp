<%@page import="java.sql.ResultSet"%>
<%@page import="com.hotal.entity.Food"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
    <title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/qiantai/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="${pageContext.request.contextPath}/qiantai/detail/stylesheet" type="text/css" href="style/css/index_1.css" />
<link href="${pageContext.request.contextPath}/qiantai/detail/style/css/index.css" rel="stylesheet" type="text/css" />
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 显示菜品的div -->
			<div id="top">
				<ul>
					<!-- 循环列出餐品 -->
					<%!	int pageSize=6;/* 一页的容量 */
 						int pageCount;/*页面数  */
 						int showPage;/* 当前是第几页 */ 
 						%>
					<%	List<Food> list=(ArrayList<Food>)request.getAttribute("foods");
						
 						int recordCount=list.size();
 						  //计算分页后的总数 
  						pageCount=(recordCount%pageSize==0)?(recordCount/pageSize):(recordCount/pageSize+1);
  						 //获取用户想要显示的页数：
						String integer=request.getParameter("showPage");
						if(integer==null){
							integer="1";
  						}
  						try{
  							showPage=Integer.parseInt(integer);
						}catch(NumberFormatException e){
						  	showPage=1;
						}
						if(showPage<=1){
						   	showPage=1;
						}
						if(showPage>=pageCount){
						   	showPage=pageCount;
						}
						//如果要显示第showPage页，那么游标应该移动到的position的值是：
  						int position=(showPage-1)*pageSize;
  						//设置游标的位置
  						pageContext.setAttribute("position", position);
  						pageContext.setAttribute("pageSize", pageSize);
  						pageContext.setAttribute("pageCount", pageCount);
					 	
					 %>
					
					 <c:forEach var="item" items="${requestScope.foods}" varStatus="vs" begin="${pageScope.position}" end="${pageScope.position+pageScope.pageSize-1}">
					 	<li>
							<dl><%-- <c:out value="${item.id}"></c:out> --%>
								<dt>
									<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findFoodById&id=${item.id}">
										<img width="214px" height="145px" src="${pageContext.request.contextPath}/${item.img}" />
									</a>
								</dt>
								<dd class="f1">
									<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findFoodById&id=${item.id}">${item.foodName}</a>
								</dd>
								<dd class="f2">
									<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findFoodById&id=${item.id}">&yen;${item.price}</a>
								</dd>
							</dl>
						</li>
						
					 </c:forEach>
					 	
						
					
				</ul>
			</div>
			
			<!-- 底部分页导航条div -->
			<div id="foot">
				
					
					
						<span
							style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
					
				
				<div id="btn">
					<ul>
						<!-- 参看 百度, 谷歌是 左 5 右 4 -->
							<c:set var="num" value="${pageScope.pageCount}"></c:set>
							<c:forEach var="i" varStatus="vs" begin="1" end="${num}">
								<li>
									<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=粤菜&showPage=${vs.count}">${vs.count}</a>
								</li>
							</c:forEach>
							
								
						
					</ul>
				</div>
				
					
						<span style="float:right; line-height:53px; margin-right:10px;  ">
							<a
							href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=粤菜&showPage=<%=showPage+1%>"
							style=" text-decoration:none;color:#000000; font-weight:bold">&gt;&gt;</a>
						</span>
					
					
				
			</div>
			
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath}/servlet/QOrderServlet?orderId=${requestScope.orderId}">
							<img src="${pageContext.request.contextPath}/qiantai/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=粤菜">粤菜</a>
						</li>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=川菜">川菜</a>
						</li>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=湘菜">湘菜</a>
						</li>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&typeName=东北菜">东北菜</a>
						</li>
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath}/servlet/QFoodServlet" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="thing">
								<input type="hidden" value="${sessiontScope.tableId}" name="tableId"/>
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="#">
									<img src="${pageContext.request.contextPath}/qiantai/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>