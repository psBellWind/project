<%@page import="com.hotal.entity.DinnerTable"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/houtai/detail/style/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/houtai/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/houtai/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/houtai/detail/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/houtai/detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="${pageContext.request.contextPath}/servlet/DinnerTableServlet" method="post">
		<input type="hidden" value="search" name="thing">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
		
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
        <%-- <% List<DinnerTable> list=(ArrayList<DinnerTable>)request.getAttribute("findAllTable");
        			System.out.print(list.size());
        		 %> --%>
        	<c:forEach var="list" items="${requestScope.findAllTable }" varStatus="status">
        		
        		<tr class="TableDetail1">
        			<td align="center">${status.count}&nbsp;</td>
        			<td align="center"> ${list.tableName}&nbsp;</td>
        			<c:if test="${list.tableStatus==1 }">
        				<td align="center">预定</td>
        			</c:if>
        			<c:if test="${list.tableStatus==0 }">
        				<td align="center">空闲</td>
        			</c:if>
					
					<td align="center">${list.orderDate}&nbsp;</td>
					<td>
					<c:if test="${list.tableStatus==1 }">
        				<a href="${pageContext.request.contextPath}/servlet/DinnerTableServlet?thing=tuizhuo&id=${list.id}" class="FunctionButton">退桌</a>
        			</c:if>
        			<c:if test="${list.tableStatus==0 }">
        				<a href="${pageContext.request.contextPath}/servlet/DinnerTableServlet?thing=yuding&id=${list.id}" class="FunctionButton">预定</a>
        			</c:if>
									
					<a href="${pageContext.request.contextPath}/servlet/DinnerTableServlet?thing=delete&id=${list.id}" onClick="delConfirm();"class="FunctionButton">删除</a>				
				</td>
        		</tr>
        	
        	</c:forEach>
			
			<!--< tr class="TableDetail1">
				<td align="center">1&nbsp;</td>
				<td align="center"> 纽约&nbsp;</td>
				<td align="center">预定</td>
				<td align="center">2017-12-08 23:31:12</td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">2&nbsp;</td>
				<td align="center"> 巴黎&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=2&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=2" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">3&nbsp;</td>
				<td align="center"> 丹麦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=3&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=3" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr>
        
			<tr class="TableDetail1">
				<td align="center">5&nbsp;</td>
				<td align="center"> 伦敦&nbsp;</td>
				<td align="center">空闲</td>
				<td align="center"></td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=5&isBook=1" class="FunctionButton">预定</a>				
					<a href="/wirelessplatform/board.html?method=delete&id=5" onClick="return delConfirm();"class="FunctionButton">删除</a>				
				</td>
			</tr> -->
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/houtai/detail/saveBoard.jsp">添加</a></div>
    </div> 
</div>
</body>
</html>
