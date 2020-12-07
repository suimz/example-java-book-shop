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
			
			<br><br><br><br><br>
			<span style="color:#7abd54;font-size:2em;">
				<i class="fa fa-check fa-2x" style="color:#7abd54;"></i>下单成功！我们将会很快为您发货
				<a href="${ctxPath }/OrderServlet?opt=show" class="btn btn-danger">查看订单</a>
				<br>
				<span>你还可以<a href="${ctxPath }/index.jsp">继续选购</a></span>
			</span>			
		</div>
		
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>