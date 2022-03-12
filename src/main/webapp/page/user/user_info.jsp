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
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
	<div class="layui-container layui-col-space15" style="margin: 20px auto; width: 50%;">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">员工信息</div>
					<div class="layui-card-body">
						<form class="layui-form">
						<div class="layui-form-item">
						<!-- readonly只读，不可编辑 -->
					      <input type="text" name="title" placeholder="请输入员工编号" 
					      		readonly="readonly" class="layui-input" value="${sessionScope.staff.id}">
						</div>
						<div class="layui-form-item">
						  <input type="text" name="title" placeholder="请输入员工姓名" id="staffName"
						  		class="layui-input" value="${sessionScope.staff.name}">
						</div>
						<div class="layui-form-item">
							<select name="s_sex" id="sex">
								<option value="" >--请选择性别--</option>
						  		<option value="0" 
						  			<c:if test="${sessionScope.staff.sex == 0}">selected</c:if>
						  		>女</option>
						  		<option value="1" 
						  			<c:if test="${sessionScope.staff.sex == 1}">selected</c:if>
						  		>男
						  		</option>
						  	</select>
						</div>
						<div class="layui-form-item">
						  <input type="text" name="title" placeholder="请输入新密码" id="staffPass" 
						  		class="layui-input" value="${sessionScope.staff.pass}">
						</div>
		                </form>
						<div class="layui-form-item" align="center">
							<button class="layui-btn layui-btn-fluid" onclick="save();">保存</button>
						</div>
					</div>
				</div> 
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script>
	function save(){
		var staffName = $("#staffName").val();
		var staffPass = $("#staffPass").val();
		var sex = $("#sex").val();
		$.ajax({
			url:"st",
			data:{"method":"updateUserInfo", "name":staffName, "pass":staffPass,"sex":sex},
			dataType:"text",//后台返回给前端页面的数据格式
			type:"post",
			async: false,
			success:function(str){
				console.info(str);
				if(str == '1'){
					alert("保存成功");
					//刷新页面
					window.location.reload();
				} else {
					alert("保存成功");
				}
			},
			error:function(){	
			}
		});	
    }
</script>
</html>
    