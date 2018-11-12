<%@page import="com.teng.bean.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
   <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
   <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script></head>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">查找方式</a>
    </div>
    <div>
        <form action="<%=request.getContextPath()%>/search" class="navbar-form navbar-left" role="search">
	<div id="search">	
		<select name="key" class="form-control">
			<option value="name">用户名</option>
			<option value="userclass">用户类型</option>
			<option value="hobby">爱好</option>
			<option value="sex">性别</option>
		</select>
		<input type="text" name="value" id="keyword" value="" class="form-control" placeholder="关键字">
		<input type="submit" value="查询" class="btn btn-default">
		<a href="main.jsp" target="show" class="btn btn-default navbar-btn">添加用户信息</a>
	</div>
		</form>
    </div>
	</div>
</nav>
	<%
		if(request.getAttribute("list")!=null&&request.getAttribute("list")!="") {
			List<User> list=(List<User>)request.getAttribute("list");
	%>
		<table class="table table-striped">
		  <thead>
			<tr>
				<td>用户名</td><td>用户类型</td><td>性别</td><td>兴趣爱好</td><td>电子邮件</td><td>自我介绍</td><td>出生日期</td><td>操作</td>
			</tr>
		  </thead>
		  <tbody>
				<% for(User user:list){ %>
				<tr>
				<td><%=user.getName() %></td>
				<td><%=user.getUserclass() %></td>
				<td><%=user.getSex() %></td>
				<td><%=user.getHobby() %></td>
				<td><%=user.getEmail() %></td>
				<td><%=user.getOther() %></td>
				<td><%=user.getDate() %></td>
				<td>
					<a href="<%=request.getContextPath()%>/delete?name=<%=user.getName()%>">删除</a>&nbsp;
					<a href="<%=request.getContextPath()%>/find?name=<%=user.getName()%>">修改</a>
				</td>
				</tr>
				<% } %>
		  </tbody>
		</table>	
	<% 
		}
	%>
	
</body>
</html>