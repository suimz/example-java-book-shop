<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
欢迎 ${name },祝你生活愉快！<br>

<table>
<tr>
	<td>
		<c:if test="${sessionScope.clist !=nul }">
			<ul>
				<c:forEach items="${sessionScope.clist}" var="cty">
					<li><a href="BookServlet?opt=byCategory&id=${cty.id}">${cty.name}</a></li>
				</c:forEach>
			</ul>
		</c:if>
	</td>
	<td>
		<c:if test="${sessionScope.blist !=null}" >
			<table border="1" cellspacing="0" cellpadding="0" width="100%" style="border:1px solid #d0d0d0;">
			<tr><th>书名</th><th>价格</th><th>ISBN</th></tr>
			<c:forEach items="${sessionScope.blist}" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.unitPrice }</td>
					<td>${book.isbn }</td>
				</tr>
			</c:forEach>
			</table>
		</c:if>	
	</td>

</tr>
</table>

</body>
</html>