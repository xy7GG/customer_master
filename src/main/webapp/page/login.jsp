<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>登陆页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-bg-black" style="font-family: '微软雅黑';">
	<div class="layui-main" style="margin: 200px auto 0 auto;width:30%; height:200px;">
		<div class="layui-panel">
		    <div align="center" style="margin-top: 10px;">
				<span class="layui-font-20">客户关系管理系统</span>
		    </div>
		    <div class="layui-form-item" style="margin: 10px auto;width: 90%;">
		      	<input type="text" class="layui-input" placeholder="请输入账号" required id="user"/>
		      	<input type="password" class="layui-input" placeholder="请输入密码" required id="pass"/>
		      	<button type="button" class="layui-btn layui-btn-normal layui-btn-fluid" onclick="login();">
					  登录
				</button>
		    </div>
		</div>   
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function login(){
		var user = $("#user").val();
		var pass = $("#pass").val();
		//ajax:jquery发送http请求的方式
		$.ajax({			                                                                        
			//提交的url路径
			url:"http://localhost:8080/gym_manger/st",
			//提交的参数
			data:{"method":"login","user":user,"pass":pass},
			//返回值数据类型
			dataType:"text",
			//提交方式：post
			type:"POST",
			//发送请求成功之后的回调函数
			success:function(str){
				console.info(str);
				if(str == '1'){
					alert("管理员登录成功");
					//跳转到主页
					window.location.href="http://localhost:8080/gym_manger/st?method=mainPage";
				} 
				else if(str == '2') 
				{
					alert("员工登录成功");
					//跳转到主页
					window.location.href="http://localhost:8080/gym_manger/st?method=userPage";
				} 
				else{
					alert("登录失败");
				}
			},
			   error:function(){			
			}
		});
	}
</script>
</html>
