<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta charset="UTF-8">
    <title></title>
    <script src="/static/bootstrap/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/static/bootstrapDengLu/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/static/bootstrapDengLu/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/static/bootstrapDengLu/css/component.css" />
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>基站机房费用提醒及风险防范系统</h3>
						<form action="#" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input id="loginNumber" type="text"  style="border:0px ;color: white;background:none" class="text"  type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input   id="pwd" class="text kb" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2" >
								<%--<button id="keyBtn"class="act-but submit"onclick="login()";style="color: #FFFFFF" style="width: 334px" > 登录</button>--%>
								<a class="act-but submit"   href="javascript:login();" style="color: #FFFFFF">登录</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="/static/bootstrapDengLu/js/TweenLite.min.js"></script>
		<script src="/static/bootstrapDengLu/js/EasePack.min.js"></script>
		<script src="/static/bootstrapDengLu/js/rAF.js"></script>
		<script src="/static/bootstrapDengLu/js/demo-1.js"></script>
		<div style="text-align:center;">
</div>
	</body>
	<script type="text/javascript">


	function login(){
        $.ajax({
			url:'<%=request.getContextPath()%>/user/login',
			type:'post',
			data:{
				loginNumber:$("#loginNumber").val(),
				password:$('#pwd').val(),
			},
			dataType:'json',
			success:function(result){
				if(result.code != 200){
					alert(result.msg);
				}else{
					window.location.href="<%=request.getContextPath()%>/user/toIndex";
				}
			}
		})
	}
    $(".kb").keypress(function(e){
        var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
        if (eCode == 13){
            login();
        }
    })


	</script>
</html>