<#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LOGIN</title>
        <script  src="<@spring.url '/assets/js/jquery/1.11.2/jquery.min.js' />" ></script>
        <link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap.min.css' />" type="text/css" />
    </head>
    <body id="login">
	   	<div id="login-wrapper" class="png_bg">
	   		<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<center>
						<img src="<@spring.url '/assets/images/logo1.png' />" />
					</center>
				</div>
				<div class="col-md-4">
				</div>
			</div>
	   		<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<center>
						<h3>
							${app}
						</h3>
					</center>
				</div>
				<div class="col-md-4">
				</div>
			</div>
			<br />
			
			<div id="logindiv" class="col-md-6 col-md-offset-3">
				<form class="form-horizontal" method="post" action="/doLogin">
					<input type="hidden" name="appid" value="${appId}" />
  					<div class="form-group">
    					<label for="labelusername" class="col-sm-2 control-label">用户名</label>
	    				<div class="col-sm-8">
	      					<input type="email" class="form-control" id="username" name="username" placeholder="输入公司邮箱">
	    				</div>
  					</div>
					<div class="form-group">
						<label for="labelpassword" class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-8">
					   		<input type="password" class="form-control" id="password" name="password" placeholder="">
					   		
					    </div>
					    <div class="col-sm-2">
					    	<a id="repass_button">更改密码</a>
					    </div>
					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">登录</button>
    					</div>
  					</div>
				</form>
			</div>
		</div>
		
		
<script type="text/javascript">
$(document).ready(function() {

	$("#repass_button").click(function() {
			var email = $("#username").val();
			var emailPattern = /^[a-zA-Z0-9_-]+@loukou.com$/;
			if (!email.match(emailPattern)) {
				alert('请输入公司邮箱！（@loukou.com）');
				return false;
			}
			
			var appId = "${appId}";
		
			window.location.href = "repass?email=" + email + "&appId=" + appId;
		});


});

</script>
		
		
		
    </body>
    </html>