<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${ctxPath }/css/bootstrap.css" />
<link rel="stylesheet" href="${ctxPath }/css/fontawesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctxPath }/css/style.css" />
<script src="${ctxPath }/js/jquery.js"></script>
<script src="${ctxPath }/js/bootstrap.js"></script>
</head>
<body class="header">
<div id="cHeader">
	<div class="row">
		<div class="col-xs-3">
			<a href="${ctxPath}/index.jsp" title="回到首页"><img src="${ctxPath }/images/logo.png" height="80px"/></a>
		</div>
		<div class="col-xs-6">
			<form action="${ctxPath}/BookServlet"  method="get">
			<!-- 搜索图书 -->
			<div class="input-group" id="search">
				<input type="text" class="form-control" name="value" placeholder="书名、分类、出版社..."/>
				<span class="input-group-btn">
					<select class="btn btn-defualt" name="opt" >
						<option value="byTitle">书名</option>
						<option value="byCategoryName">分类</option>
						<option value="byIsbn">ISBN</option>
						<option value="byPublish">出版社</option>
					</select>
				</span>
				<span class="input-group-btn">
					<input type="submit" class="btn btn-info" value="搜索" />
				</span>
			</div>
			
			</form>
		</div>
		<div class="col-xs-3 userInfo">
			<p>
				<a href="${ctxPath }/UserServlet?opt=show">我的账户</a>&nbsp;|&nbsp;
				<a href="${ctxPath }/OrderServlet?opt=show">我的订单</a>&nbsp;|&nbsp;
				<a href="${ctxPath }/CartServlet?opt=show">我的购物车</a>
			</p>
			<c:if test="${user==null}">
				<p>[&nbsp;<a href="${ctxPath}/login.jsp">登录</a>&nbsp;|&nbsp;<a href="${ctxPath}/register.jsp">免费注册</a>&nbsp;]</p>
			</c:if>
			<c:if test="${user!=null}">
				<p>${user.loginId}，欢迎您来此购书！[<a href="${ctxPath }/UserServlet?opt=logout">安全退出</a>]</p>
			</c:if>
		</div>
	</div>
</div>
	
<hr class="customhr"/>
</body>
</html>