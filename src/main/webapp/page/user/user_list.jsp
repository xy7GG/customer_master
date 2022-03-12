<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入JSTL标签库:是一个JSP标签的集合，封装了许多jsp应用程序通用的核心功能-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
	<div class="layui-container layui-col-space15" style="margin: 20px auto; width: 100%;">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">查询</div>
					<div class="layui-card-body">
						<form class="layui-form">
							<div class="layui-form-item layui-inline" style="width: 20%;">
						      <input type="text" name="title" required placeholder="请输入编号" 
						      	class="layui-input" id="staffId">
							</div>
							<div class="layui-form-item layui-inline" style="width: 20%;">
							  <input type="text" name="title" required placeholder="请输入姓名" 
							  	class="layui-input" id="staffName">
							</div>					
						</form>
						<div class="layui-form-item layui-inline" style="width: 10%;margin-left: 5px;">
							<button class="layui-btn layui-btn-sm" onclick="search();">查询</button>
						</div>
					</div>
				</div> 
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">员工列表</div>
					<div class="layui-card-body">
						<div class="layui-form-item layui-inline" style="width: 10%;margin-left: 5px;">
							<button class="layui-btn layui-btn-sm" onclick="openWin();">添加</button>
						</div>
						<table class="layui-table">
						
<%-- 						<colgroup>
								<col width="5%">
							    <col width="8%">
							    <col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="12%">
								<col width="10%">
							    <col>
							</colgroup> --%>
							  <thead>
							    <tr>
									<th>
										<input type="checkbox" />
									</th>
									<th>序号</th>
									<th>员工编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>更新</th>
									<th>删除</th>
							    </tr> 
							  </thead>
							  <tbody>
							  <c:forEach items="${list}" var="staff" varStatus="no">
							  	<tr>
									<td><input type="checkbox" /></td>
									<td>${no.index+1}</td>
									<td>${staff.id }</td>
									<td>${staff.name }</td>
									<td>
									<c:if test="${staff.sex == 1}">
									男
									</c:if>
									<c:if test="${staff.sex == 0}">
									女
									</c:if>
									</td>
								    <td>
								    <button class="layui-btn layui-btn-sm" onclick="openUpWin('${staff.id}');">更新</button>								    
								    </td>
								    <td>
								    <button class="layui-btn layui-btn-sm" onclick="del(${staff.id});">删除</button>
								    </td>
							    </tr>
							  </c:forEach>
							    
							  </tbody>
						</table>
					</div>
				</div>  
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
	//注册layui弹层组件
	layui.use(['layer'], function(){
		var layer=layui.layer;
	});
	
	function openWin(){		
		layer.open({
			type: 2,
			title: "添加用户信息",
			area: ['450px', '530px'],
			content: "st?method=addUserPage"
		});
	}	
	
	function openUpWin(id){
		layer.open({
			type: 2,
			title: "更新用户信息",
			area: ['450px', '530px'],
			content: "st?method=upUserPage&id="+id
		});
	}
</script>

<script type="text/javascript">
	function search(){
		var staffId = $("#staffId").val();
		var staffName = $("#staffName").val();
		
		var params = "&staffId="+staffId;
		params += "&staffName="+staffName;
		var url = "st?method=userList"+params
		console.info(url);
		window.location.href=url;	
	}
	function del(id){
		if(confirm("确定删除吗？"))
		{
			$.ajax({
				url:"st",
				data:{"method":"del","id":id},
				dataType:"text",
				type:"post",
				success:function(str){
					if(str == "1"){
						alert("删除成功");
						//刷新当前页面
						window.location.reload();
					} else {
						alert("删除失败");
					}	
				}
			});
		}
	}
</script>
</html>
