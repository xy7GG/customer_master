<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">

<body>
	<div class="layui-container layui-col-space15" style="margin: 20px auto 0 auto; width: 100%;">
		<div class="layui-row">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-body">
						<form class="layui-form">
							<div class="layui-form-item">
						      <input type="text" placeholder="请输入客户编号" 
						      	class="layui-input" name="id">
							</div>
						
							<div class="layui-form-item">
							<input type="text" placeholder="请输入客户姓名" 
							  	class="layui-input" name="cileName">
							</div>
							<div class="layui-form-item">
							  <select lay-verify="required" name="sex">
							  	<option value="1">男</option>
							  	<option value="0">女</option>
							  </select>
							</div>
							<div class="layui-form-item">
							<input type="text" placeholder="请输入客户消费金额" 
							  	class="layui-input" name="value">
							</div>	
							<div class="layui-form-item">
						      <input type="text" placeholder="请输入为客户分配的员工的编号" 
						     	class="layui-input" name="stfId">
							</div>
							<input type="hidden" name="method" value="add" />
						</form>
						<div class="layui-form-item" align="center">
							<button class="layui-btn layui-btn-fluid" onclick="add();">保存</button>
						</div>
					</div>
				</div> 
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
function add(){
	var id = $("input[name='id']").val();
	var stfId =$("input[name='stfId']").val();
		if(id == null || id == "")
		{
			alert("请输入客户编号"); 
	    }
		else if(stfId==null || stfId==""){
			 alert("请输入为该客户分配的员工编号");
		} 
        else{
		//序列化form表单数据
		var params = $("form").serialize();
		console.info(params);
		//提交表单数据
		$.ajax({
        	url:"dt", //你的路由地址
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
}
</script>
</html>