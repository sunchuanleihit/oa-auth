<#import "/spring.ftl" as spring/>
<#import "left.ftl" as left/>
<#macro page title>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}</title>
        <script src="<@spring.url '/assets/include/jquery/1.11.2/jquery.min.js' />"></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap.min.js' />"></script>
        <script src="<@spring.url '/assets/include/My97DatePicker/WdatePicker.js' /> "></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap-datepicker.min.js' />"></script>
        <script src="<@spring.url '/assets/include/bootstrap/3.3.4/js/bootstrap-datepicker.zh-CN.min.js' />"></script>
        <script src="<@spring.url '/assets/include/My97DatePicker/WdatePicker.js' />"></script>
        <script src="<@spring.url '/assets/include/ssputils.js' />"></script>
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
        <script>
            jQuery(document).ready(function() {    
                $('#main-nav .menu .sub-menu li a').each(function(){
                    $(this).click(function(){
                        $("#main-nav a").removeClass("current");
                        $(this).addClass("current").parents('.menu').find('.nav-top-item').addClass('current');
                    });
                });
                
                $('.datepicker').datepicker({
                     format: "yyyy/mm/dd",
                     language: "zh-CN",
                     orientation: "top left",
                     autoclose:true
                });
                
                var text, $option;
                for (var i = 0; i < 24; i++) {
                    text = i < 10 ? "0" + i +":00" : i + ":00";
                    $option=$("<option></option>").val(i).text(text);
                    $('.hourSelector').append($option);
                };
                $('.hourSelector').first().val("8");
                $('.hourSelector').last().val("17");
                
            });
        </script>
    </head>
    <body>
    <div style="width:30%">
        <@left.page "<#if session?exist >${session.siteName}</#if>" />
    </div>
    <div style="margin-left:230px;float:right;position:absolute;right:30px;left:30px;">
      <#nested>
    </div>
  
    </body>
    </html>
</#macro>