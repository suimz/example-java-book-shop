<%@page import="com.suimz.mybookshop.shoppingcart.ShoppingCart"%>
<%@page import="com.suimz.mybookshop.shoppingcart.ShoppingItem"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车 - 个人中心</title>
</head>
<body>
<!-- header -->
<jsp:include page="../header.jsp" />
		
<div id="container">
	<!-- 个人中心菜单 -->
	<div class="usermenu pull-left">
		<jsp:include page="usermenu.jsp" />
	</div>
	
	<!-- info -->
	<div class="useropt pull-right">
		<h4>我的购物车</h4><br>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>图书</th>
					<th>单价(元)</th>
					<th width="120px" class="text-center">数量</th>
					<th>小计(元)</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${cart!=null && cart.itemAmount>0 && silist!=null}">
				<c:forEach items="${silist}" var="s">
					<tr>
						<td><a href="${ctxPath }/BookServlet?opt=buyBook&bid=${s.item.id}">${s.item.title }</a></td>
						<td>￥${s.item.unitPrice }</td>
						<td>
						<div class="input-group input-group-sm">
						<a href="${ctxPath }/CartServlet?opt=deAmount&isbn=${s.item.isbn}" class="input-group-addon"  style="text-decoration: none;">-</a>
						<input id="num" type="text" value="${s.amount }" class="form-control input-sm text-center" onblur="uSAmount(this.value,${s.item.isbn})"/>
						<a href="${ctxPath }/CartServlet?opt=inAmount&isbn=${s.item.isbn}" class="input-group-addon" style="text-decoration: none;">+</a>
						</div>
						</td>
						<td>${s.item.unitPrice*s.amount }元</td>
						<td><a href="${ctxPath }/CartServlet?opt=del&isbn=${s.item.isbn}">删除</a></td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${cart==null || cart.itemAmount<1 || silist==null}">
					<tr>
						<td colspan="5" class="text-center"><br>购物车中什么都没有！赶紧<a href="${ctxPath }/index.jsp">去选购</a>吧！<br></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<c:if test="${cart!=null && cart.itemAmount>0 && silist!=null}">
			<div class="pull-right">
				<span class="show_total">总计:<i>￥${cart.total}</i></span>
				<a href="${ctxPath }/OrderServlet?opt=add" class="btn btn-danger btn-sm">去结算</a>
				<a href="${ctxPath }/CartServlet?opt=clear" class="btn btn-warning btn-sm">清空购物车</a>
			</div>
		</c:if>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />


<script type="text/javascript">
	$(function() {
		
	});
	
	function uSAmount(num,isbn){
		 $.ajax({
			type:'get',
			url:'${ctxPath }/CartServlet?opt=buy',
			data:"isbn="+isbn+"&num="+num,
			error: function(request) {
                alert("修改商品数量出错");
            },
            success:function(data){
            	
    		}
		});
	}
	
</script>
</body>
</html>