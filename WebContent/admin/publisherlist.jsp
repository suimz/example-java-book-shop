<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
	    <title>出版社管理 - 系统后台</title>
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
						<li><a href="${ctxPath }/BookServlet?opt=booklist&role=3"><span class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;图书管理</a></li>
						<li><a href="${ctxPath }/admin/categorylist.jsp"><span class="glyphicon glyphicon-pushpin"></span>&nbsp;&nbsp;分类管理</a></li>
						<li class="active"><a href="${ctxPath }/admin/publisherlist.jsp"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;&nbsp;出版社管理</a></li>
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
		        <div class="col-md-12">
		            <div class="page-header">
		                <h1>出版社管理</h1>
		            </div>
		            <div class="col-md-12 pad0">
		               <!-- 添加出版社 -->
		                <form action="${ctxPath }/PublisherServlet?opt=add&role=3" method="post">
		                    <div class="col-md-10">
		                        <input class="form-control" name="name" placeholder="请输入要添加的出版社名称">
		                    </div>
		                    <div class="col-md-2">
		                        <button type="submit" class="btn btn-default">添加</button>
		                    </div>
		                </form>
		                <!-- 添加出版社 -->
		            </div>
		            <div class="col-md-12 taglist">
		            	<!-- 出版社 -->
		            	<c:forEach items="${plist}" var="p">
							<div class="alert alert-success alert-dismissible pull-left" role="alert">
			                    <button type="button" class="close" id="pid${p.id}" title="编辑" onclick="changePublisher(this)"><span class="glyphicon glyphicon-edit" style="font-size:13px;"></span></button>
			                    <strong title="${p.id}">${p.name }</strong>
			                    <input type="text" class="form-control collapse">
			                </div>
						</c:forEach>
						<!-- 出版社 -->
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 主区域 -->
		
		
		<!-- javascript代码 -->
		<script src="${ctxPath }/js/jquery.js"></script>
		<script src="${ctxPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			
			function changePublisher(dom){
				//获取元素
				var strong = $('#'+dom.id).next('strong');
				var input = $(strong).next('input');
				//显示输入框
				$(input).removeClass("collapse");
				$(input).focus();
				$(input).val($(strong).html());
				//输入框失去焦点事件
				$(input).bind({ 
					blur:function(){ 
						$(this).addClass("collapse");
						var name = $(this).val();
						var id = $(strong).attr("title");
						if(id!='' && id!=null && name!=null && name!='' && name!=$(strong).html()){
							//ajax提交修改
							$.ajax({
								type:'get',
								url:'${ctxPath }/PublisherServlet?opt=edit&role=3',
								data:"pid="+id+"&name="+name,
								error: function(request) {
				                    alert("修改失败");
				                },
				                success:function(data){
				                	$(strong).html(name);
				        		}
							});
						}
						
						
					} 
				}); 
			}
		</script>
	</body>
</html>
