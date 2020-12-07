<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心 - 在线购书</title>
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
		<h4>我的资料</h4><hr/>
		<div class="updateuser">
		<!-- 
		<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  ${MSG_USER_UPDATE_RESULT}
		</div>
		 -->
		<form action="${ctxPath }/UserServlet?opt=updateUser" method="post">
			<input type="hidden" name="id" value="${user.id }" />
			<label>姓名</label>
			<input type="text" class="form-control" name="name" placeholder="真实姓名" value="${user.name }"/>
			<label>*电子邮箱</label>
			<input type="text" class="form-control" name="mail" placeholder="邮箱" value="${user.mail }"/>
			<label>*手机号码</label>
			<input type="text" class="form-control" name="phone"  placeholder="手机号码" value="${user.phone }"/>
			<label>*联系地址</label>
			<textarea rows="3" cols="" class="form-control" name="address" placeholder="联系地址"> ${user.address}</textarea>
			<br>
			<button class="btn btn-default" type="submit">修改资料</button>
		</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />

<!-- script -->
<script type="text/javascript">
	$(function(){
		//处理提示消息
		if('${MSG_USER_UPDATE_RESULT}'==''){
			$('#msg').addClass('collapse');
			<% session.removeAttribute("MSG_USER_UPDATE_RESULT"); %>	
		}else
			$('#msg').removeClass('collapse');
	});
	
</script>
</body>
</html>