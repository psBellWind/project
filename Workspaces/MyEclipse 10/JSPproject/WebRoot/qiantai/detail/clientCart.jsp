<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/qiantai/detail/style/css/index.css" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/page_common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/qiantai/detail/style/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		/** // 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		*/
		
		$(document).ready(function(){
			$("[name=foodCount]").blur(function(){
				var $foodCount=$(this).val();
				var $price=$("[name=price]").html();
				
				var $smailPrice=(parseInt($foodCount))*(parseFloat($price));
				
				$("[name=smailPrice]").text($smailPrice);
				$("[name=totalPrice]").html("&yen;&nbsp;"+$smailPrice);
			});
			$("[name=xiadan]").click(function(){
				var $foodCount=$("[name=foodCount]").val();
				
				window.location.replace("${pageContext.request.contextPath}/servlet/QOrderServlet?thing=addCar&foodId=${requestScope.food.id}"+"&foodCount="+$foodCount);
			});
			
		});
		
		function delConfirm(){
			
			$("[name=delete]").parent().parent().remove();
				
		}
		// 下单
		/* function genernateOrder() {
			window.location.href = "${pageContext.request.contextPath}/qiantai/detail/clientOrderList.jsp";
		} */
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
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 	
				 		<tr height="60">
					 		<td align="center" width="20%">${requestScope.food.foodName}</td>
					 		<td align="center" width="20%" >￥<span name="price">${requestScope.food.price}</span></td>
					 		<td align="center" width="20%">
					 			<input type="text" value="1" size="3" lang="3" name="foodCount" />
					 		</td>
					 		<td align="center" width="20%" name="smailPrice">${requestScope.food.price}</td>
					 		<td align="center" width="20%">
					 		<input type="button" value="删除" class="btn_next" lang="3" name="delete" onClick="delConfirm()" />
					 			
					 		</td>
				 	</tr>
				 	
				

					<tr>
						<td colspan="6" align="right">总计: 
							<span style="font-size:36px;" name="totalPrice">&yen;&nbsp;${requestScope.food.price}</span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							
								
									<input type="button" name="xiadan" class="btn_next"  value="下单"/>
							
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
