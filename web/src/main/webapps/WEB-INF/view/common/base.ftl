<#import "/spring.ftl" as spring/>
<#import "left.ftl" as left/>
<#import "content.ftl" as content/>
<#macro page title>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>
        <link rel="stylesheet" href="<@spring.url '/assets/js/bootstrap/3.3.4/css/bootstrap.min.css' />"  type="text/css" />
 		<link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap-table.css' />"  type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/css/reset.css' />"  type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/css/style.css' />" type="text/css" />
        <link rel="stylesheet" href="<@spring.url '/assets/css/index.css' />" type="text/css" />
        
      <!-- <link rel="stylesheet"  href="<@spring.url '/assets/css/fileinput.min.css'/>" type="text/css">  -->

        <link rel="stylesheet" href="<@spring.url '/assets/css/css.css'/>" type="text/css">
        <!--
	    <link rel="stylesheet" href="<@spring.url '/assets/css/uniform.css'/>" type="text/css">
	    -->
	    <!-- 下拉框需要添加的样式 -->
	     <link rel="stylesheet"  href="<@spring.url '/assets/css/bootstrap-select.css'/>" type="text/css">
	    <link href="<@spring.url '/assets/js/bootstrap/3.3.4/css/bootstrap-datetimepicker.min.css' />" rel="stylesheet" type="text/css" />
 		<script  src="<@spring.url '/assets/js/jquery/1.11.2/jquery.min.js' />" ></script>
 		<script  src="<@spring.url '/assets/js/jquery-validation-1.14.0/dist/jquery.validate.min.js' />" ></script>
		<script  src="<@spring.url '/assets/js/bootstrap/3.3.4/js/bootstrap.min.js' />" ></script>
		<script src="<@spring.url '/assets/js/bootstrap/3.3.4/js/bootstrap-datetimepicker.min.js'/>" ></script>
		<script src="<@spring.url '/assets/js/bootstrap/3.3.4/js/locales/bootstrap-datetimepicker.zh-CN.js'/>" ></script>
		<!--
        <script src="<@spring.url '/assets/js/fileinput.min.js'/>"   ></script>
        
      	<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js" ></script>
        <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.js" ></script>
        -->     
        <!-- <script src="<@spring.url '/assets/js/fileinput.min.js'/>"   ></script> -->
        <script src="<@spring.url '/assets/js/ajaxfileupload.js'/>"   ></script>

        <script src="<@spring.url '/assets/js/bootstrap/bootstrap-select.js'/>"   ></script>
        <script src="<@spring.url '/assets/js/bootstrap/bootstrap-table.js'/>"   ></script>
        <script src="<@spring.url '/assets/js/bootstrap/bootstrap-table-zh-CN.js'/>"   ></script>
        
        <style type="text/css">
            body {
                padding-top: 20px;
                 padding-bottom: 20px;
            }
        </style>
        <script>
            jQuery(document).ready(function() {    
                $('#main-nav .menu .sub-menu li a').each(function(){
                    $(this).click(function(){
                        $("#main-nav a").removeClass("current");
                        $(this).addClass("current").parents('.menu').find('.nav-top-item').addClass('current');
                        
                        var url = $(this).attr("simu_href");
                        loadPage(url);
                        return false;
                    });
                });
                
                $('#main-nav .nav-top-item').each(function() {
                	$(this).next().hide();
                });	
                
                $('#main-nav .nav-top-item').click(function() {
                	$(this).next().toggle(300);
                });	
        	
        	        	
        	
        	
            });
        </script>
    </head>
    <body>

    <@left.page "local" />
    <@content.page "content" />
    <#nested/>
    </body>
    </html>
</#macro>