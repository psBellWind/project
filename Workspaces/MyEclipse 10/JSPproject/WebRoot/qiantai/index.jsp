<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	<base href="<%=basePath%>">
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/qiantai/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/qiantai/detail/style/css/index_1.css" />
	<style type="text/css">
	* {
		margin: 0px;
		padding: 0px
	}
	#dish_2 a{
		text-decoration:none;
		font-size:36px;
		color:#000;
	}
	#dish_2 ul { 
		list-style:none;
	} 
	#dish_2 li{
		background:url(${pageContext.request.contextPath}/qiantai/detail/style/images/img/btn.gif);
		width:164px;
		height:47px;
		text-align:center;
		padding-top:5px;
	}
	</style>
</head>
<body style="text-align: center">
	<!--外部的大层-->
	<div class="index_all" style="text-align:center;">
		<!--上面的背景层-->
		<div>
			<img src="${pageContext.request.contextPath}/qiantai/detail/style/images/flower.gif" />
		</div>
		<!--中间层-->
		<div id="index_center">
			<!--中间层的空白层-->
			<div id="space">
				
			</div>
			<!--中间层的菜单层-->
			<div>
				<!--菜单层的左边-->
				<div id="index_centerleft"></div>
				<!--菜单层的中间-->
				<div class="bg_middle">
					<img
						src="${pageContext.request.contextPath}/qiantai/detail/style/images/index_menu.gif"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="164,99,354,199" href="${pageContext.request.contextPath}/qiantai/#" />
					</map>
				</div>
				<!--中间层的右边-->
				<div id="index_centerright"></div>
			</div>
			<!--放桌子的层-->
			<div id="center_bottom">
				<ul style=" display:inline-table">
					<c:forEach var="en" items="${requestScope.fns}" varStatus="vs">
						<li>
							<a href="${pageContext.request.contextPath}/servlet/QFoodServlet?thing=findByTypeName&zhiling=zhiling&typeName=粤菜&tableName=${en.tableName}&tableId=${en.id}&tableStatus=1">
								 ${en.tableName}&nbsp;
							</a>
						</li>
					</c:forEach>
						<%-- <li>
							<a href="${pageContext.request.contextPath}/servlet/CaidanServlet">
								纽约&nbsp;
							</a>
						</li>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/CaidanServlet">
								丹麦&nbsp;
							</a>
						</li>
					
						<li>
							<a href="${pageContext.request.contextPath}/servlet/CaidanServlet">
								伦敦&nbsp;
							</a>
						</li> --%>
					
				</ul>
			</div>
		</div>
		
		<!--下面的背景层-->
		<div>
			<img src="${pageContext.request.contextPath}/qiantai/detail/style/images/flower.gif" />
		</div>
	</div>
</body>
</html>
