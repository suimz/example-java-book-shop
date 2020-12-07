<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>后台登录</title>
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/bootstrap.min.css"/>
	    <link rel="stylesheet" type="text/css" href="${ctxPath }/admin/css/style.css"/>
		<style type="text/css">
			body{
				background: #2c3e50;
			}
		</style>
	</head>
	<body>
		<!--登录面板-->
		<div class="panel panel-default loginbar">
			<div class="panel-heading">
				后台登录／Login
			</div>
			<div class="panel-body">
				<form action="${ctxPath }/UserServlet?opt=login&role=3" method="post">
					<div class="form-group has-feedback">
					  <input type="text" placeholder="管理员账号" name="loginId" class="form-control">
					  <i class="glyphicon glyphicon-user form-control-feedback" aria-hidden="true"></i>
					</div>
					<div class="form-group has-feedback">
					  <input type="password" placeholder="密码" name="loginPwd" class="form-control">
					  <i class="glyphicon glyphicon-lock form-control-feedback" aria-hidden="true"></i>
					</div>
					<button type="submit" class="btn btn-success btn-block">登录后台</button>
				</form>
			</div>
			<div class="panel-footer text-right"><a href="${ctxPath }/index.jsp"  class="pull-left">返回首页</a>&copy;在线购书后台管理系统</div>
		</div>
	</body>
</html>