<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码 - 个人中心</title>
</head>
<body>
<!-- header -->
<jsp:include page="../header.jsp" />
		
<div id="container">
	<!-- 个人中心菜单 -->
	<div class="usermenu pull-left">
		<jsp:include page="usermenu.jsp" />
	</div>
	
	<!-- info -->
	<div class="useropt pull-right">
		<h4>修改密码</h4><hr/>
		<div class="updateuser">
		<form class="">
			<label>原密码</label>
			<input type="password" class="form-control" name="name" placeholder="原密码"/>
			<label>新密码</label>
			<input type="password" class="form-control" name="mail" placeholder="新密码"/>
			<label>确认密码</label>
			<input type="password" class="form-control" name="phone"  placeholder="确认密码"/>
			<br>
			<button class="btn btn-default" type="submit">修改密码</button>
		</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />
</body>
</html>