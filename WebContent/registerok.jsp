<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册会员 - 在线购书</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp" />

	<!-- container -->
	<div id="container">
		<br><br><br><br><br><br>
		<h3 class="text-center">注册成功！是否&nbsp;<a href="${ctxPath }/login.jsp" style="font-size:25px;">登录</a></h3>
		<br>
		<h3 class="text-center">或者&nbsp;<a href="${ctxPath }/index.jsp" style="font-size:25px;">选购图书</a></h3>
	</div>

	<!--footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
