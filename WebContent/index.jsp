<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>首页 - 在线购书</title>
		<link rel="stylesheet" href="${ctxPath}/css/slide.css" />
		<script type="text/javascript" src="${ctxPath}/js/jquery.js"></script>
		<script src="${ctxPath}/js/slide.js" ></script>
		<script type="text/javascript">
			$(function(){
				initSlide();
			});
		</script>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="header.jsp" />
		
		<!-- content -->
		<div id="container">
			<!--图书分类列表 -->
			<ul class="cateul">
				<li class="catehead">图书分类</li>
				<c:forEach items="${applicationScope.clist}" var="cty">
					<li><a href="BookServlet?opt=byCategory&cid=${cty.id}">${cty.name}</a></li>
				</c:forEach>
			</ul>
			
			<div class="content">
				<!-- 幻灯片 -->
				<div id="slide">
					<ul>
					<li>
						<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/01.jpg" alt="" /></a></div>
					</li>
					<li>
						<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/01.jpg" alt="" /></a></div>
					</li>
					</ul>
				</div>
				
				<!-- 人气推荐 -->
				<div class="goodsbar">
					<span class="goodsbar-title">人气图书</span>
					<div class="goodsbar-content">
						<c:forEach var="book" items="${hotBList }" begin="0" end="3">
							<dl class="bookBlock">
								<dd class="img"><a href="BookServlet?opt=buyBook&bid=${book.id}"><img src="images/book.png" class="" /></a></dd>
								<dt><a href="BookServlet?opt=buyBook&bid=${book.id}">${book.title}</a></dt>
								<dd class="price">单价:￥<span class="unitprice">${book.unitPrice}</span><span class="pull-right">浏览&nbsp;${book.clicks}</span></dd>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
			
		</div>
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>
