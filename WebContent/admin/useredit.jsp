<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<div class="page-header">
			            <h1>用户管理</h1>
			        </div>
					<ul class="nav nav-tabs">
				        <li class="active">
				            <a href="user_list.html">编辑用户</a>
				        </li>
				    </ul>
				    
				    <div>
				    	<form action="${ctxPath }/UserServlet?opt=edit&role=3" method="post">
				    		<div class="form-group">
		                        <label for="addname">UID</label>
		                        <input type="hidden" name="uid" value="${editUser.id }">
		                         <input type="hidden" name="address" value="${editUser.address }">
		                      	<span style="margin-left: 30px;">${editUser.id }</span>
		                    </div>
		                    <div class="form-group">
		                        <label for="addname">用户名</label>
		                        <input type="text" name="loginId" value="${editUser.loginId }" class="form-control" placeholder="用户名">
		                    </div>
		                    <div class="form-group">
		                        <label for="addemail">真实姓名</label>
		                        <input type="text" name="name" value="${editUser.name }" class="form-control" placeholder="请输入用户邮箱">
		                    </div>
		                    <div class="form-group">
		                        <label for="addemail">用户邮箱</label>
		                        <input type="text" name="mail" value="${editUser.mail}" class="form-control" placeholder="请输入用户邮箱">
		                    </div>
		                    <div class="form-group">
		                        <label for="addemail">用户手机</label>
		                        <input type="text" name="phone" value="${editUser.phone}" class="form-control" placeholder="请输入用户邮箱">
		                    </div>
		                    <div class="form-group">
		                        <label for="addyonghuzu">角色</label>
		                        <select name="userRoleId" class="form-control">
		                            <option value="1">普通用户</option>
		                            <option value="2">VIP 用户</option>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label for="addyonghuzu">状态</label>
		                        <select name="userStateId" class="form-control">
		                            <option value="1">正常</option>
		                            <option value="2">锁定</option>
		                        </select>
		                    </div>
		                    <button type="submit" class="btn btn-primary">确认修改</button>
		                    <a href="${ctxPath }/admin/userlist.jsp" class="btn btn-default">取消</a>
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
	</body>
</html>
