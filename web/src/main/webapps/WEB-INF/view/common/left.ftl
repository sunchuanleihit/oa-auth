<#import "/spring.ftl" as spring/>
<#macro page  admin>
<div id="sidebar">
            <div id="sidebar-wrapper">
                <a href="<@spring.url '#'/>">
                    <div style="font-size:22px; color: #f1f1f1; padding: 7px;width: 100%; text-align: center; ">楼口运营后台</div>
                </a>
                <div id="profile-links">
                    你好,
                    <a id="admin_logout_button" href="#">登出</a>
                    <br />
                </div>
                <ul id="main-nav">
                <#list tabs as parentTab>
                    <li class="menu">
                        <a href="javascript:void(0);" class="nav-top-item ">${parentTab.text}</a>
                        <ul class="sub-menu">
                          <#list parentTab.subtabs as subtab>
                            <li>
                                <a simu_href="<@spring.url '${subtab.path}'/> " module-name="${parentTab.moduleName}">${subtab.text}</a>
                            </li>
                            </#list>
                        </ul>
                    </li>
                </#list>
                </ul>
            </div>
        </div>
        
        <script type="text/javascript">
        $(document).ready(function() {
        	$("#admin_logout_button").click(function() {
        		$.ajax( {    
				    url:'../auth/do_logout',// 跳转到 action    
				    data:{},    
				    type:'post',    
				    cache:false, 
				    dataType:'json',    
				    success:function(data) {    
				        if (data.code == 200) {
				        	window.location.href="../main/index";
				        }else {
				        	alert("登陆失败， 用户名或密码错误！");
				        }
				     },    
				     error : function() {    
				          // view("异常！");    
				          alert("异常！");    
				     }    
				});  
        	
        	
        	
        	});
        
        
        });
        
        </script>
        
</#macro>