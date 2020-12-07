<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册会员 - 在线购书</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp" />

	<!-- container -->
	<div id="rgbar">
		<p>用户注册／<small>Register</small></p>
		<form action="${ctxPath }/UserServlet?opt=register" method="post" onsubmit="return check_Rg_Submit()">
		<ul class="rgul">
			<div class="alert alert-warning" role="alert" id="msg">
			  ${MSG_USER_REGISTER_RESULT}
			</div>
			<li>
				<span>用户名</span>
				<input class="form-control" type="text" name="loginId" placeholder="用户名*" onblur="check_Rg_LoginId(this)"/>
				<small id="rg_LoginId_state"></small>
			</li>
			<li class="tips collapse" id="rg_LoginId_Tips"><span></span><code>用户名由6-12位字母、数字、下划线组成</code></li>
			<li>
				<span>用户密码</span>
				<input class="form-control" type="password" name="loginPwd"  placeholder="用户密码*" onblur="check_Rg_LoginPwd(this)" />
				<small id="rg_LoginPwd_state"></small>
			</li>
			<li class="tips collapse" id="rg_LoginPwd_Tips"><span></span><code>密码由6-20位字母、数字、下划线组成</code></li>
			<li>
				<span>确认密码</span>
				<input class="form-control" type="password" placeholder="确认密码*" onblur="check_Rg_LoginPwd_Success(this)"/>
				<small  id="rg_LoginPwd2_state"></small>
			</li>
			<li class="tips collapse" id="rg_LoginPwd2_Tips"><span></span><code>两次密码不一致</code></li>
			<li><hr /></li>
			<li>
				<span>真实姓名</span>
				<input class="form-control" type="text" name="name" placeholder="姓名" onblur="check_Rg_Name(this)" />
				<small id="rg_Name_state"></small>
			</li>
			<li class="tips collapse" id="rg_Name_Tips"><span></span><code>请输入合法的中文姓名</code></li>
			<li>
				<span>电子邮箱</span>
				<input class="form-control" type="text" name="mail" placeholder="邮箱*" onblur="check_Rg_Email(this)" />
				<small id="rg_Email_state"></small>
			</li>
			<li class="tips collapse" id="rg_Email_Tips"><span></span><code>邮箱格式有误</code></li>
			<li>
				<span>手机号码</span>
				<input class="form-control" type="text" name="phone" placeholder="手机*" onblur="check_Rg_Phone(this)" />
				<small id="rg_Phone_state"></small>
			</li>
			<li class="tips collapse" id="rg_Phone_Tips"><span></span><code>手机号码有误</code></li>
			<li>
				<span>联系地址</span>
				<input class="form-control" type="text" name="address" placeholder="联系地址*" onblur="check_Rg_Address(this)" />
				<small id="rg_Address_state"></small>
			</li>
			<li class="tips collapse" id="rg_Address_Tips"><span></span><code>联系地址不能为空</code></li>
			<li><span></span><label><input type="checkbox"  onchange="check_Rg_Article(this)"/>同意协议《<a
					href="#">阅读协议</a>》</label></li>
			<li><span></span><button type="submit" >立即注册</button></li>
		</ul>
		</form>
		<div class="note">
			<ul>
				<li>已有账号?<a href="login.jsp">立即登录</a></li>
				<li>用户名由6-12位字母、数字、下划线组成</li>
				<li>密码由6-20位字母、数字、下划线组成</li>
			</ul>
		</div>
	</div>

	<!--footer -->
	<jsp:include page="footer.jsp" />
	
	<!-- script -->
	<script type="text/javascript" src="${ctxPath }/js/userckeck.js"></script>
<script type="text/javascript">
	$(function(){
		//处理提示消息
		if('${MSG_USER_REGISTER_RESULT}'==''){
			$('#msg').addClass('collapse');
			<% session.removeAttribute("MSG_USER_REGISTER_RESULT"); %>	
		}else
			$('#msg').removeClass('collapse');
	});
	
</script>
</body>
</html>
