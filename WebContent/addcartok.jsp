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
		<div id="container" class="text-center">
			<c:if test="${user==null }">
				<div class="alert alert-warning alert-dismissible" role="alert" style="color:#FF6633;">
					<i class="fa fa-exclamation-triangle" style="color:#FFCC33;"></i>&nbsp;
					您还没有登录！登录后即可继续操作&nbsp;&nbsp;
					<a href="${ctxPath }/login.jsp" class="btn btn-danger btn-sm" style="height:25px;line-height: 13px;">立即登录</a>
				</div>
			</c:if>
			<br><br><br><br><br>
			<span style="color:#7abd54;font-size:2em;">
				<i class="fa fa-check fa-2x" style="color:#7abd54;"></i>商品已成功加入购物车！
				<a href="${ctxPath }/CartServlet?opt=show" class="btn btn-danger">去购物车结算</a>
				<br>
				<span>你还可以<a href="${ctxPath }/index.jsp">继续选购</a></span>
			</span>			
		</div>
		
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>