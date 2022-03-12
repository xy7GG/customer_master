<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						      	class="layui-input" id="cileId">
							</div>
							<div class="layui-form-item layui-inline" style="width: 20%;">
							  <input type="text" name="title" required placeholder="请输入姓名" 
							  	class="layui-input" id="cileName">
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
					<div class="layui-card-header">客户消费金额列表</div>
					<div class="layui-card-body">
						<table class="layui-table">
							  <thead>
							    <tr>
							        <th>客户id</th>
									<th>客户姓名</th>
									<th>客户消费金额</th>
									<th>更新</th>
							    </tr> 
							  </thead>
							  	<col width="10%">
								<col width="10%">
								<col width="10%">
								<col width="5%">							
							
							  <tbody>
							  <c:forEach items="${value}" var="cile" varStatus="no">
							     <tr>
							        <td>${cile.id}</td>	
									<td>${cile.cileName}</td>					
									<td>${cile.value}</td>
									<td>
								    <button class="layui-btn layui-btn-sm" onclick="openUpWin('${cile.id}');">更新</button>
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
	function openUpWin(id){
		layer.open({
			type: 2,
			title: "更新信息",
			area: ['450px', '530px'],
			content:"dt?method=toUpPage&id="+id
		});
	}
</script>
<script type="text/javascript">
<!-- 查找客户信息 -->
	function search(){
		var cileId = $("#cileId").val();
		var cileName = $("#cileName").val();
		var params = "&cileId="+cileId;
		params += "&cileName="+cileName;
		var url = "dt?method=CileList2"+params
		console.info(url);
		window.location.href=url;	
	}
</script>
</html>