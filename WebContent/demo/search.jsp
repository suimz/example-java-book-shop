<%@page import="com.suimz.mybookshop.entity.Books"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../BookServlet" method="get">
<p>查询:
	<select name="opt">
		<option value="byTitle">书名</option>
		<option value="byIsbn">ISBN</option>
	</select>
<input type="text" name="title"/><input type="submit" value="查询" /></p>
</form>

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
