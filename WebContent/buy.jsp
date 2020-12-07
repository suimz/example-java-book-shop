<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>${buyBook.title } - 图书详情</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/style.css" />
	</head>
	<body>
		<!-- header -->
		<jsp:include page="header.jsp" />
		
		<!-- content -->
		<div id="container" class="buy">
		
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">图书详情</h3>
				</div>
				<div class="panel-body">
					<div class="bookpic pull-left">
						<img src="${ctxPath }/images/book.png" class="img-polaroid" />
					</div>
					<div class="bookinfo pull-left">
						<form action="${ctxPath }/CartServlet?opt=buy&isbn=${buyBook.isbn }" method="post">
						<ul>
							<li class="title">${buyBook.title }</li>
							<li class="" style="font-size:14px;">作者:${buyBook.author}</li>
							<li class="price">￥${buyBook.unitPrice}</li>
							<li class="number">
								<p class=" pull-left" style="line-height: 35px;font-size:14px;">数量&nbsp;</p>
								<div class="input-group input-group-sm" style="width:100px;">
									<a href=" javascript:void(0);" onclick="deAmount()" class="input-group-addon"  style="text-decoration: none;">-</a>
									<input id="num" name="num" type="text" value="1" class="form-control input-sm text-center"/>
									<a href=" javascript:void(0);" onclick="inAmount()" class="input-group-addon" style="text-decoration: none;">+</a>
								</div>
								<!-- 数量增减 -->
								<script type="text/javascript">
									//加
									function inAmount(){
										var num = $("#num").val();
										var re = /^[0-9]*[1-9][0-9]*$/;
										if(re.exec(num)){
											num++;
										}else{
											num=1;
										}
										$("#num").val(num);
									}
									//减
									function deAmount(){
										var num = $("#num").val();
										var re = /^[0-9]*[1-9][0-9]*$/;
										if(re.exec(num) && num>1){
											num--;
										}else{
											num=1;
										}
										$("#num").val(num);
									}
								</script>
							</li>
							<li><br><button type="submit" class="btn btn-danger">加入购物车</button> </li>
						</ul>
						</form>
						<br><br><br>
					</div>
					<!-- 详细信息开始 -->
					<div class="introbar">
					<div style="clear: both;"></div>
					<h4>详细信息</h4><hr>
						<table  width="100%">
							<tr>
								<th>作者</th>
								<td>${buyBook.author}</td>
								<th>出版社</th>
								<td>xxx</td>
								<th>出版时间</th>
								<td>${buyBook.publishDate}</td>
							</tr>
							<tr>
								<th>ISBN</th>
								<td>${buyBook.isbn}</td>
								<th>字数</th>
								<td>${buyBook.wordsCount}</td>
								<th>销量</th>
								<td>${buyBook.clicks}</td>
							</tr>
						</table>
						<hr>
						<H3>内容介绍</H3>
						<div class="introbar-content">${buyBook.contentDescription}</div>
						<hr><H3>作者介绍</H3>
						<div class="introbar-content">${buyBook.authorDescription}</div>
						<hr><H3>TOC</H3>
						<div class="introbar-content">${buyBook.toc}</div>
					</div>
					<!-- 详细信息结束 -->
					
				</div>
			</div>
		</div>
		
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>