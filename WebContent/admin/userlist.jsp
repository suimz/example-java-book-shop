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
	    <title>用户管理 - 系统后台</title>
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
						<li class="active"><a href="${ctxPath }/UserServlet?opt=userlist&role=3"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
						<li><a href="${ctxPath }/BookServlet?opt=booklist&role=3"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;图书管理</a></li>
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
			            <a href="${ctxPath }/UserServlet?opt=userlist&role=3" class="list-group-item active">用户管理</a>
			            <a href="${ctxPath }/admin/useradd.jsp" class="list-group-item">添加用户</a>
		            </div>
				</div>
				<!-- 子导航 -->
				
				<!-- 内容 -->
				<div class="col-md-10">
					<!-- 操作提示信息 -->
					<div class="alert alert-warning alert-dismissible" role="alert" id="msg">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>提示:</strong>${ADMIN_MSG_USER}
					</div>
					<!-- 操作提示信息 -->
					
					<div class="page-header">
			            <h1>用户管理 - <code>共${fn:length(ulist.sourceList)}位用户</code></h1>
			        </div>
					<ul class="nav nav-tabs">
				        <li class="active">
				            <a href="user_list.html">用户列表</a>
				        </li>
				        <li  class="pull-right">
				        	<!-- 分页
							<nav class="pull-right" style="margin-top: -20px;">
								<ul class="pagination">
									<li>
										<c:if test="${ulist.currentPage<=1}">
											<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage}" aria-label="Previous">
											<span aria-hidden="true">上一页</span>
											</a>
										</c:if>
										<c:if test="${ulist.currentPage>1}">
											<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage-1}" aria-label="Previous">
											<span aria-hidden="true">上一页</span>
											</a>
										</c:if>
									</li>
									<li>
										<c:if test="${ulist.currentPage<ulist.totalPage}">
											<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage+1}" aria-label="Next">
											<span aria-hidden="true">下一页</span>
											</a>
										</c:if>
										<c:if test="${ulist.currentPage>=ulist.totalPage}">
											<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.totalPage}" aria-label="Next">
											<span aria-hidden="true">下一页</span>
											</a>
										</c:if>
									</li>
								</ul>
							</nav> -->
				            <!-- 分页 -->
				        </li>
				    </ul>
				     <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>UID</th>
		                        <th>用户名</th>
		                        <th>姓名</th>
		                        <th>邮箱</th>
		                        <th>状态</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <tbody  style="font-size:13px">
		                <c:if test="${ulist!=null}">
			                <c:forEach items="${ulist.dataList}" var="u">
			              		<c:if test="${u.id!=user.id}">
								 <tr>
			                        <th scope="row">${u.id}</th>
			                        <td>${u.loginId}</td>
			                        <td>${u.name}</td>
			                        <td>${u.mail}</td>
			                       <c:if test="${u.userStateId==1}">
			                       	<td style="color: #00B32D;"><span class="glyphicon glyphicon-ok"></span>&nbsp;正常</td>
			                       </c:if>
			                       <c:if test="${u.userStateId==2}">
			                       	<td style="color: #CC0033;"><span class="glyphicon glyphicon-lock"></span>&nbsp;锁定</td>
			                       </c:if>
			                        <td>
			                            <div role="presentation" class="dropdown">
			                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" style="font-size:13px">操作<span class="caret"></span></button>
			                                <ul class="dropdown-menu">
			                                    <li><a href="${ctxPath }/UserServlet?opt=show&role=3&uid=${u.id}">编辑</a></li>
			                                    <li><a href="javascript:if(confirm('确认要删除该用户吗?'))location='${ctxPath }/UserServlet?opt=del&uid=${u.id}'">删除</a></li>
			                                   <c:if test="${u.userStateId==1}">
			                                	 <li><a href="${ctxPath }/UserServlet?opt=lock&role=3&uid=${u.id}">锁定</a></li>
			                                   </c:if>
			                                    <c:if test="${u.userStateId!=1}">
			                                	 <li><a href="${ctxPath }/UserServlet?opt=unlock&role=3&uid=${u.id}">解锁</a></li>
			                                   </c:if>
			                                </ul>
			                            </div>
			                        </td>
			                    </tr>
			                    </c:if>
							</c:forEach>
						</c:if>
		                </tbody>
		            </table>
		            <!-- 分页 -->
		            <nav class="pull-right" style="margin-top: -20px;">
						<ul class="pagination">
						<li><a href="javascript:void(0)">当前第${ulist.currentPage}页,共${ulist.totalPage}页,每页10条数据</a></li>
							<li>
								<c:if test="${ulist.currentPage<=1}">
									<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage}" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
									</a>
								</c:if>
								<c:if test="${ulist.currentPage>1}">
									<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage-1}" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
									</a>
								</c:if>
							</li>
							
							 <c:forEach var="index" begin="1" end="${ulist.totalPage}" step="1"> 
								<c:if test="${index==ulist.currentPage}">
									<li class="active"><a href="#">${index}</a></li>
								</c:if>
								<c:if test="${index!=ulist.currentPage}">
									<li><a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${index}">${index}</a></li>
								</c:if>
							 </c:forEach>
							<li>
								<c:if test="${ulist.currentPage<ulist.totalPage}">
									<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.currentPage+1}" aria-label="Next">
									<span aria-hidden="true">下一页</span>
									</a>
								</c:if>
								<c:if test="${ulist.currentPage>=ulist.totalPage}">
									<a href="${ctxPath }/UserServlet?opt=userlist&role=3&page=${ulist.totalPage}" aria-label="Next">
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
				if('${ADMIN_MSG_USER}'==''){
					$('#msg').addClass('collapse');
					<% session.removeAttribute("ADMIN_MSG_USER"); %>	
				}else
					$('#msg').removeClass('collapse');
			});
		</script>
	</body>
</html>
