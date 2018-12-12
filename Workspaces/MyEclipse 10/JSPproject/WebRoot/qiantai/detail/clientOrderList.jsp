<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/qiantai/detail/style/css/index.css" />
	<script type="text/javascript">
		// 通知服务员结账
		function callPay(node) {
			alert('尊敬的顾客,您好!已经通知服务员结账，请稍等!');
			var orderId = node.lang;
			window.location.href = "${pageContext.request.contextPath}/servlet/QOrderServlet"+
					"?thing=jiezhang&totalPrice=${requestScope.totalPrice}";}
		
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 	</tr>
					<c:forEach var="od" items="${requestScope.od}" varStatus="vs">
						<tr height="60">
					 		<td align="center" width="20%">${od.foodName}</td>
					 		<td align="center" width="20%">￥${od.foodPrice}</td>
					 		<td align="center" width="20%">${od.foodCount}</td>
					 		<td align="center" width="20%">${od.foodCount*od.foodPrice}</td>
				 		</tr>
					
					</c:forEach>

					<!-- <tr height="60">
					 		<td align="center" width="20%">烤乳猪</td>
					 		<td align="center" width="20%">￥68.0</td>
					 		<td align="center" width="20%">1</td>
					 		<td align="center" width="20%">68.0</td>
				 		</tr> -->
				 	

					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;</span>
							<label
								id="counter" style="font-size:36px">${requestScope.totalPrice}</label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="结账" class="btn_next" lang="" onclick="callPay(this)"/>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath}/servlet/QOrderServlet?tableId=${requestScope.tableId}&orderId=${requestScope.orderId}">
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
