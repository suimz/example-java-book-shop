<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
	    <title>图书管理 - 系统后台</title>
	    <link rel="stylesheet" type="text/css" href="${ctxPath }/css/bootstrap.min.css"/>
	    <link rel="stylesheet" type="text/css" href="${ctxPath }/admin/css/style.css"/>
	</head>
	</head>
	<body>
		<!-- 导航栏 -->
		<nav class="navbar navbar-default">
			<div class="container">
				<!-- 小屏幕按钮和logo -->
				<div class="navbar-header">
					<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="${ctxPath }/admin/index.jsp" class="navbar-brand">后台管理</a>
				</div>
				<!-- 小屏幕按钮和logo -->
				<!-- 导航 -->
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="${ctxPath }/admin/index.jsp"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;后台首页</a></li>
						<li><a href="${ctxPath }/UserServlet?opt=userlist&role=3"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
						<li class="active"><a href="${ctxPath }/BookServlet?opt=booklist&role=3"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;图书管理</a></li>
						<li><a href="${ctxPath }/admin/categorylist.jsp"><span class="glyphicon glyphicon-pushpin"></span>&nbsp;&nbsp;分类管理</a></li>
						<li><a href="${ctxPath }/admin/publisherlist.jsp"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;&nbsp;出版社管理</a></li>
					</ul>
					<!-- 个人操作 -->
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
						  <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    admin
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" aria-labelledby="dLabel">
							<li class=""><a href="${ctxPath }/index.jsp"><span class="glyphicon glyphicon-new-window"></span>&nbsp;&nbsp;前台首页</a></li>
							<li class=""><a href="${ctxPath }/UserServlet?opt=logout&role=3"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
						  </ul>
						</li>
					</ul>
					<!-- 个人操作 -->
				</div>
			</div>
		</nav>
		<!-- 导航栏 -->
		
		<!-- 主区域 -->
		<div class="container">
			<div class="row">
				<!-- 子导航 -->
				<div class="col-md-2">
				   <div class="list-group">
		               <a href="${ctxPath }/BookServlet?opt=booklist&role=3" class="list-group-item active">图书管理</a>
			            <a href="${ctxPath }/admin/bookadd.jsp" class="list-group-item">添加图书</a>
		            </div>
				</div>
				<!-- 子导航 -->
				<!-- 内容 -->
				<div class="col-md-10">
					<!-- 操作提示信息 -->
					<div class="alert alert-warning alert-dismissible" role="alert" id="msg">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>提示:</strong>${ADMIN_MSG_BOOK}
					</div>
					<!-- 操作提示信息 -->
					<div class="page-header">
			            <h1>图书管理</h1>
			        </div>
					<ul class="nav nav-tabs">
				        <li class="active">
				            <a href="user_list.html">编辑图书</a>
				        </li>
				    </ul>
				    
				    <div>
				    	<form action="${ctxPath }/BookServlet?opt=edit&role=3" method="post">
				    		<div class="form-group">
		                        <label for="addname">BID</label>
		                        <input type="hidden" name="bid" value="${editBook.id }">
		                      	<span style="margin-left: 30px;color: #666;">${editBook.id }</span>
		                    </div>
		                    <div class="form-group">
		                        <label for="title">书名*</label>
		                        <input type="text" name="title" value="${editBook.title}" class="form-control" placeholder="书名">
		                    </div>
		                    <div class="form-group">
		                        <label for="unitPrice">单价(元)*</label>
		                        <input type="text" name="unitPrice" value="${editBook.unitPrice }" class="form-control" placeholder="单价">
		                    </div>
		                    <div class="form-group">
		                        <label for="author">作者</label>
		                        <input type="text" name="author" value="${editBook.author }" class="form-control" placeholder="作者">
		                    </div>
		                    <div class="form-group">
		                        <label for="cate">所属分类</label>
		                        <select name="categoryId" class="form-control">
		                            <c:forEach items="${clist}" var="c">
										<c:if test="${c.id==editBook.categoryId}">
											<option selected ="selected" value="${c.id}">${c.name }</option>
										</c:if>
										<c:if test="${c.id!=editBook.categoryId}">
											<option value="${c.id}">${c.name }</option>
										</c:if>
									</c:forEach>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label for="publish">出版社</label>
		                        <select name="publisherId" class="form-control">
		                            <c:forEach items="${plist}" var="p">
										<c:if test="${p.id==editBook.publisherId}">
											<option selected ="selected" value="${p.id}">${p.name }</option>
										</c:if>
										<c:if test="${p.id!=editBook.publisherId}">
											<option value="${c.id}">${p.name }</option>
										</c:if>
									</c:forEach>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label for="PublishDate">出版时间*</label>
		                        <input type="text" name="publishDate" value="${editBook.publishDate }" class="form-control" placeholder="出版时间">
		                    </div>
		                    <div class="form-group">
		                        <label for="ISBN">ISBN*</label>
		                        <input type="text" name="ISBN" value="${editBook.isbn }" class="form-control" placeholder="ISBN">
		                    </div>
		                    <div class="form-group">
		                        <label for="WordsCount">字数</label>
		                        <input type="text" name="wordsCount" value="${editBook.wordsCount}" class="form-control" placeholder="字数">
		                    </div>
		                    <div class="form-group">
		                        <label for="ContentDescription">内容介绍</label>
		                        <textarea class="form-control" name="contentDescription" rows="6">${editBook.contentDescription}</textarea>
		                    </div>
		                    <div class="form-group">
		                        <label for="AuthorDescription">作者介绍</label>
		                        <textarea class="form-control" name="authorDescription" rows="6">${editBook.authorDescription}</textarea>
		                    </div>
		                    <button type="submit" class="btn btn-primary">确认修改</button>
		                    <a href="${ctxPath }/admin/booklist.jsp" class="btn btn-default">取消</a>
		                </form>
				    </div>
				    
				    
				</div>
				<!-- 内容 -->
			</div>
		</div>
		<!-- 主区域 -->
		
		
		<!-- javascript代码 -->
		<script src="${ctxPath }/js/jquery.js"></script>
		<script src="${ctxPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//处理提示消息
				if('${ADMIN_MSG_BOOK}'==''){
					$('#msg').addClass('collapse');
					<% session.removeAttribute("ADMIN_MSG_BOOK"); %>	
				}else
					$('#msg').removeClass('collapse');
			});
		</script>
	</body>
</html>
