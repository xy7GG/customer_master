<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库:是一个JSP标签的集合，封装了许多jsp应用程序通用的核心功能-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>主页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
	<div class="layui-container layui-col-space5" style="width: 100%; margin: 0;">
		<div class="layui-row">
			<div class="layui-col-md12 layui-bg-black" style="height: 54px;">
				<div style="margin: 4px 10px auto auto;" align="right">					
					<div style="margin-top: 12px;">
					<!-- 获取session中的名字叫staff的值 -->
					<c:if test="${sessionScope.staff == null}">
					<!-- 未登录点击头像跳转到登陆页 -->
					<span onclick="window.location.href='st?method=loginPage'">
						<img src="${pageContext.request.contextPath}/img/head01.png" width="30px" height="30px" />
						<span>&nbsp;未登录</span>
					</span>
					</c:if>					
					<c:if test="${sessionScope.staff != null and sessionScope.staff.sex == 0}">
						<img src="${pageContext.request.contextPath}/img/head03.png" width="30px" height="30px"  />
						<span>${sessionScope.staff.name}</span>
					</c:if>
					<c:if test="${sessionScope.staff != null and sessionScope.staff.sex == 1}">
						<img src="${pageContext.request.contextPath}/img/head02.png" width="30px" height="30px"  />
						<span>${sessionScope.staff.name}</span>
					</c:if>					
					</div>				
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md2 layui-bg-black" style="height: 590px;">
				<ul class="layui-nav layui-nav-tree" lay-filter="test" style="width: 100%;" lay-shrink="all">
					<li class="layui-nav-item layui-nav-itemed">
						<a href="javascript:">个人信息</a>
						<dl class="layui-nav-child">
							<dd class="layui-this"><a href="st?method=userInfoPage" target="main">个人信息</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:">客户关系管理</a>
						<dl class="layui-nav-child">
						  <dd><a href="st?method=userList" target="main">员工管理</a></dd>
						  <dd><a href="dt?method=toCilePage" target="main">客户管理</a></dd>		
						  <dd><a href="dt?method=toValuePage" target="main">消费记录管理</a></dd>				  
						</dl>
					</li>
					<li class="layui-nav-item"><a href="st?method=logout">退出登录</a></li>
				</ul>
			</div>
			<div class="layui-col-md10" style="height: 590px;background-color: #D9D9D9;">
				<!-- 嵌入页面 -->
				<iframe name="main" src="st?method=userInfoPage" frameborder="0" style="width: 100%; height: 100%;"></iframe>
			</div>			
		</div>		
	</div>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</html>
