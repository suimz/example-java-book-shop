<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的购物车 - 在线购书</title>
		<link rel="stylesheet" href="${ctxPath }/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctxPath }/css/style.css" />
	</head>
	<body>
		<!-- header -->
		<jsp:include page="header.jsp" />
		
		<!-- content -->
		<div id="container" >
			<c:if test="${user==null }">
				<div class="alert alert-warning alert-dismissible" role="alert" style="color:#FF6633;">
					<i class="fa fa-exclamation-triangle" style="color:#FFCC33;"></i>&nbsp;
					您还没有登录！登录后即可继续操作&nbsp;&nbsp;
					<a href="${ctxPath }/login.jsp" class="btn btn-danger btn-sm" style="height:25px;line-height: 13px;">立即登录</a>
				</div>
			</c:if>
			<!-- 购物车列表 -->
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
			<c:if test="${cart!=null && cart.itemAmount>0 }">
				<div class="pull-right">
					<span class="show_total">总计:<i>￥${cart.total}</i></span>
					<a href="${ctxPath }/login.jsp" class="btn btn-danger  btn-sm">去结算</a>
					<a href="${ctxPath }/CartServlet?opt=clear" class="btn btn-warning btn-sm">清空购物车</a>
				</div>
				
			</c:if>
		</div>
		
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>