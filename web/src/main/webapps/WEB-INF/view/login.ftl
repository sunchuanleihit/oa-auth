<#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LOGIN</title>
        <link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap.min.css' />" type="text/css" />
    </head>
    <body id="login">
	   	<div id="login-wrapper" class="png_bg">
	   		<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<center>
						<h1>
							后台系统
						</h1>
					</center>
				</div>
				<div class="col-md-4">
				</div>
			</div>
			
			
			<div id="logindiv" class="col-md-4 col-md-offset-4">
				<form class="form-horizontal" method="post" action="/doLogin">
  					<div class="form-group">
    					<label for="labelusername" class="col-sm-2 control-label">用户名</label>
	    				<div class="col-sm-10">
	      					<input type="text" class="form-control" id="username" name="username" placeholder="">
	    				</div>
  					</div>
					<div class="form-group">
						<label for="labelpassword" class="col-sm-2 control-label">密码</label>
					    <div class="col-sm-10">
					   		<input type="password" class="form-control" id="password" name="password" placeholder="">
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
    </body>
    </html>