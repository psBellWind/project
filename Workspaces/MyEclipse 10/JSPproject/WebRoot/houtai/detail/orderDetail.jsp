<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/houtai/detail/
					style/css/images/title_arrow.gif" /> 订单菜品列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>菜名</td>
					<td>单价</td>
					<td>数量</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach var="list" items="${requestScope.orderDetails}" varStatus="vs">
					<tr height="60">
				 		<td>${list.foodName}</td>
				 		<td>${list.price}</td>
				 		<td>${list.foodCount}</td>
			 		</tr>
				
				</c:forEach>
			 		<!-- <tr height="60">
				 		<td>烤乳猪</td>
				 		<td>68.0</td>
				 		<td>1</td>
			 		</tr>
			 	
			 		<tr height="60">
				 		<td>烤乳猪</td>
				 		<td>68.0</td>
				 		<td>1</td>
			 		</tr>
			 	
			 		<tr height="60">
				 		<td>白切鸡</td>
				 		<td>68.0</td>
				 		<td>1</td>
			 		</tr> -->
			 	
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			 <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
		</div>
	</div>
</body>
</html>