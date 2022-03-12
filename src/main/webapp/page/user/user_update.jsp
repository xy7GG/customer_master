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
	<div class="layui-container layui-col-space15" style="margin: 20px auto 0 auto; width: 100%;">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body">
						<form class="layui-form">
							<div class="layui-form-item">
						      <input type="text" name="id" placeholder="请输入员工编号" 
						      	class="layui-input" value="${staff.id}" readonly="readonly">
							</div>
							<div class="layui-form-item">
							  <input type="text" name="name" placeholder="请输入员工姓名" 
							  	class="layui-input" value="${staff.name }">
							</div>
							<div class="layui-form-item">
							  <input type="text" name="user" placeholder="请输入账号" 
							  	class="layui-input" value="${staff.user}">
							</div>
							<div class="layui-form-item">
							  <input type="text" name="pass" placeholder="请输入密码" 
							  	class="layui-input" value="${staff.pass}">
							</div>
							<div class="layui-form-item" align="center">
								<button class="layui-btn layui-btn-fluid" onclick="update();">保存</button>
							</div>
							<input type="hidden" value="upUserInfo" name="method"/>
						</form>
					</div>
				</div> 
			</div>
		</div>	
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
	function update() {
		//序列化form表单数据
		var params = $("form").serialize();
		console.info(params);
		//提交表单数据
		$.ajax({
        	url:"st", //你的路由地址
           	type:"POST",
           	dataType:"text",
           	data: params,
           	success:function(str){
            	if(str > 0){
            		alert("保存成功");
            	} else {
            		alert("保存失败");
            	}
           	},
           	error:function(XMLHttpRequest, textStatus, errorThrown){
           		alert("提交错误");
           		console.info(XMLHttpRequest.status+","+XMLHttpRequest.readyState+","+textStatus);
        	}
        });
		console.info("close layer");
		//关闭弹出层
		var index = parent.layer.getFrameIndex(window.name);  
	    parent.layer.close(index);//关闭当前页
	    //父页面刷新
	    parent.location.reload();
	}
</script>
</html>
