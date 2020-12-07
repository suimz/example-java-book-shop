<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			            <h1>图书管理 - <code>共${fn:length(blist.sourceList)}本书</code></h1>
			        </div>
					<ul class="nav nav-tabs">
				        <li class="active">
				            <a href="user_list.html">图书列表</a>
				        </li>
				        <li  class="pull-right">
				        	<!-- 分页 -->
				            <nav style="margin-top: -20px;">
								<ul class="pagination">
								<li><a href="javascript:void(0)">当前第${blist.currentPage}页,共${blist.totalPage}页,每页20条数据</a></li>
									<li>
										<c:if test="${blist.currentPage<=1}">
											<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage}" aria-label="Previous">
											<span aria-hidden="true">上一页</span>
											</a>
										</c:if>
										<c:if test="${blist.currentPage>1}">
											<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage-1}" aria-label="Previous">
											<span aria-hidden="true">上一页</span>
											</a>
										</c:if>
									</li>
									<li>
										<c:if test="${blist.currentPage<blist.totalPage}">
											<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage+1}" aria-label="Next">
											<span aria-hidden="true">下一页</span>
											</a>
										</c:if>
										<c:if test="${blist.currentPage>=blist.totalPage}">
											<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.totalPage}" aria-label="Next">
											<span aria-hidden="true">下一页</span>
											</a>
										</c:if>
									</li>
								</ul>
							</nav>
				            <!-- 分页 -->
				        </li>
				    </ul>
				     <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>BID</th>
		                        <th>书名</th>
		                        <th>分类</th>
		                       <!--  <th>出版社</th> -->
		                        <th>人气</th>
		                        <th>单价(元)</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <tbody style="font-size:13px">
		                <c:if test="${blist!=null}">
			                <c:forEach items="${blist.dataList}" var="b">
								 <tr>
			                        <th scope="row">${b.id}</th>
			                        <td>${b.title}</td>
			                        <td>
			                        <!-- 分类 -->
			                         <c:forEach items="${clist}" var="c">
										<c:if test="${b.categoryId==c.id}">${c.name}</c:if>
									</c:forEach>
			                        <!-- 分类 -->
			                        </td>
			                       	<!-- 出版社
			                       	<td>
			                         <c:forEach items="${plist}" var="p">
										<c:if test="${b.publisherId==p.id}">${p.name}</c:if>
									</c:forEach>
									</td>
			                       	 -->
			                       	<td >${b.clicks}</td>
			                       	<td >${b.unitPrice}</td>
			                        <td style="font-size:13px">
			                        	<div role="presentation" class="dropdown">
			                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">操作<span class="caret"></span></button>
			                                <ul class="dropdown-menu">
			                                    <li><a href="${ctxPath }/BookServlet?opt=show&role=3&bid=${b.id}">编辑</a></li>
			                                    <li><a href="javascript:if(confirm('确认要删除该图书吗?'))location='${ctxPath }/BookServlet?opt=del&role=3&bid=${b.id}'">删除</a></li>
			                                </ul>
			                            </div>
			                        </td>
			                    </tr>
							</c:forEach>
						</c:if>
		                </tbody>
		            </table>
		             <!-- 分页 -->
		            <nav class="pull-right" style="margin-top: -20px;">
						<ul class="pagination">
							<li><a href="javascript:void(0)">当前第${blist.currentPage}页,共${blist.totalPage}页,每页20条数据</a></li>
							<li>
								<c:if test="${blist.currentPage<=1}">
									<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage}" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
									</a>
								</c:if>
								<c:if test="${blist.currentPage>1}">
									<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
									</a>
								</c:if>
							</li>
							 <!-- 
							 <c:forEach var="index" begin="1" end="${blist.totalPage}" step="1"> 
								<c:if test="${index==blist.currentPage}">
									<li class="active"><a href="#">${index}</a></li>
								</c:if>
								<c:if test="${index!=blist.currentPage}">
									<li><a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${index}">${index}</a></li>
								</c:if>
							 </c:forEach>
							  -->
							<li>
								<c:if test="${blist.currentPage<blist.totalPage}">
									<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">下一页</span>
									</a>
								</c:if>
								<c:if test="${blist.currentPage>=blist.totalPage}">
									<a href="${ctxPath }/BookServlet?opt=booklist&role=3&page=${blist.totalPage}" aria-label="Next">
									<span aria-hidden="true">下一页</span>
									</a>
								</c:if>
							</li>
						</ul>
					</nav>
		            <!-- 分页 -->
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
