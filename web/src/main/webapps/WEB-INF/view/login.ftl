<#import "/spring.ftl" as spring/>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>LOGIN</title>
        <script src="<@spring.url '/assets/include/jquery/1.11.2/jquery.min.js' />"></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap.min.js' />"></script>
        <script src="<@spring.url '/assets/include/My97DatePicker/WdatePicker.js' /> "></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap-datepicker.min.js' />"></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap-datepicker.zh-CN.min.js' />"></script>
        <script src="<@spring.url '/assets/include/My97DatePicker/WdatePicker.js' />"></script>
        <link rel="stylesheet" href="<@spring.url '/assets/css/style.css' />" type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/include/bootstrap/3.3.4/css/bootstrap-datepicker3.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/include/bootstrap/3.3.4/css/bootstrap.min.css' />" type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/css/main.css' />" type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/include/My97DatePicker/skin/WdatePicker.css'/>" type="text/css" />
        <style type="text/css">
            body {
                padding-top: 50px;
                 padding-bottom: 20px;
            }
        </style>
        <script type="text/javascript">
            if(top != self)
            {
                top.location.href = self.location.href;
            }
        </script>
    </head>
    <body id="login">
	   	<div id="login-wrapper" class="png_bg">
			<div id="login-top">
				<h1>
					楼口财务系统
				</h1>
				
				<a href="http://www.loukou.com/">
					<img id="logo" src="<@spring.url '/assets/images/logo.png' />">
				</a>
			</div>
			
			<div id="login-content">
				<form method="POST" id="LoginForm" novalidate="novalidate" action="/login">
					<p>
						<label>
							用户名
						</label>
						<input class="text-input valid" type="text" name="username" aria-required="true" aria-invalid="false" aria-describedby="user_name-error">
					<span id="user_name-error" class="error success valid"></span></p>
					<div class="clear">
					</div>
					<p>
						<label>
							密码
						</label>
						<input class="text-input valid" type="password" name="password" aria-required="true" aria-invalid="false" aria-describedby="password-error">
					<span id="password-error" class="error success valid"></span></p>
					<div class="clear">
					</div>
					<p id="remember-password">
						<label>
							<input type="checkbox">
							记住我
						</label>
					</p>
					<div class="clear">
					</div>
					<p>
						<input class="button" type="submit" value="登录" id="login-btn">
					</p>
				</form>
			</div>
		</div>	
  
    </body>
    </html>