<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
</head>
<body>

<c:if test="${olist==null}">
您还没有订单！
</c:if>

<c:if test="${olist!=null}">
	<h3>我的订单</h3><hr>
	<table>
		<tr>
			<th>图书</th>
			<th>购买数量</th>
			<th>总价</th>
			<th>下单时间</th>
		</tr>
		<c:forEach items="${olist}" var="o">
			<tr>
				<td>${o.title }</td>
				<td>${o.quantity }</td>
				<td>${o.quantity*o.unitPrice}</td>
				<td>${o.orderDate}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>