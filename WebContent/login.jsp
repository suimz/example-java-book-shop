<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>会员登录 - 在线购书系统</title>
<link rel="stylesheet" href="${ctxPath }/css/style.css" />
<link rel="stylesheet" href="${ctxPath }/css/fontawesome/css/font-awesome.min.css" />
<style type="text/css">
#container .imgs{
	min-widthl:600px;
	max-width:600px;
	min-height:300px;
	max-height:300px;
	margin-top:30px;
}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp" />

	<div class="container-fluid" style="background-color: #57DBB4;height:430px;margin-top:-20px;">
		<div id="container" class="row">
			<div class="col-xs-7">
				<img class="imgs" src="images/loginimg.png" />
			</div>
			<div class="col-xs-4">
				<div class="loginbar">
				<span class="login_header">会员登录<span class="login_right">还没有账号？<a
						href="register.jsp" title="立即注册">立即注册</a></span></span>
				<hr>
				<div class="login_tips" id="login_tips">
					&nbsp;<i class="fa fa-volume-up"></i>&nbsp;建议不要在公共场合下记住密码,以免账号丢失
				</div>
				<!-- 登录表单 -->
				<form action="UserServlet?opt=login" method="post" onsubmit="return check_Login_Submit();">
					<div class="login_input">
						<span class="login_lefticon"><i class="fa fa-user"></i></span><input
							type="text" name="loginId" class="login_txt" placeholder="账号" onblur="check_Login_Id(this)">
					</div>
					<div class="login_input">
						<span class="login_lefticon"><i class="fa fa-lock"></i></span><input
							type="password" name="loginPwd" class="login_txt" placeholder="密码" onblur="check_Login_Pwd(this)">
					</div>
		
					<div class="login_input">
						<input type="submit" value="登　录" class="login_btn">
					</div>
				</form>
				<div class="login_input">
					<a href="index.jsp">返回首页</a>
				</div>
			</div>
			</div>
		</div>
	</div>
	
	<!--footer -->
	<jsp:include page="footer.jsp" />
	
	<!-- js -->
	<script type="text/javascript" src="${ctxPath }/js/userckeck.js"></script>
</body>
</html>
