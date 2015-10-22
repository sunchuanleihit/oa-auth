<#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>重置密码</title>
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
	   		
			<br />
			
			<div id="logindiv" class="col-md-6 col-md-offset-3">
				<form class="form-horizontal" method="post" action="/doLogin">
					<input type="hidden" name="appid" value="${appId}" />
  					<div class="form-group">
    					<label  for="labelusername" class="col-sm-2 control-label">用户名</label>
	    				<div class="col-sm-8">
	      					<span id="email_span">${email}</span>
	    				</div>
  					</div>
					<div class="form-group">
						<label for="old_password" class="col-sm-2 control-label">原密码</label>
					    <div class="col-sm-8">
					   		<input type="password" class="form-control" id="old_password"  placeholder="">
					    </div>
					</div>
					<div class="form-group">
						<label for="new_password" class="col-sm-2 control-label">新密码</label>
					    <div class="col-sm-8">
					   		<input type="password" class="form-control" id="new_password"  placeholder="">
					    </div>
					   
					</div>
					<div class="form-group">
						<label for="confirm_password" class="col-sm-2 control-label">确认新密码</label>
					    <div class="col-sm-8">
					   		<input type="password" class="form-control" id="confirm_password"  placeholder="">
					    </div>
					    <div class="col-sm-2">
					    	
					    </div>
					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="button" id="modify_pass_button" class="btn btn-default">修改</button>
    					</div>
  					</div>
				</form>
			</div>
		</div>

<script type="text/javascript">

	$(document).ready(function() {
	
		$("#modify_pass_button").click(function() {
			var email = $("#email_span").html();
			var oldPassword = $("#old_password").val();
			var newPassword = $("#new_password").val();
			var confirmPassord = $("#confirm_password").val();
		
			var blankPattern = /^\s*$/;
			if (email.match(blankPattern)) {
				alert("邮箱不得为空！");
				return false;
			}
			if (oldPassword.match(blankPattern)) {
				alert("旧密码不得为空！");
				return false;
			}
			if (newPassword.match(blankPattern)) {
				alert("新密码不得为空！");
				return false;
			}
			if (confirmPassord.match(blankPattern)) {
				alert("新密码确认不得为空！");
				return false;
			}
			
			if (newPassword != confirmPassord) {
				alert("新密码与确认密码不一致！");
				return false;
			}
			
			
			$.ajax( {    
			    url:'../user/doRepass', 
			    data:{ 
			    	email: email,
			    	oldPassword: oldPassword,
			    	newPassword: newPassword
			    },    
			    type:'put',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	 if (data.code == 200) {
			    	 	alert("修改密码成功，请用新密码登陆！");
			    	 	window.location.href="login?appid=${appId}";
			    	 } else {
			    	 	alert("创建失败！");
			    	 }
			    },    
			    error : function() {      
			          alert("异常！");    
			    }    
			}); 
		
		
		});
		
	});


</script>
		
    </body>
    </html>