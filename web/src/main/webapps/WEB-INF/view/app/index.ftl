
<form>
  <div class="form-group">
    <label for="domain_prefix">跳转域名前缀</label>
    <input type="text"  class="form-control" id="domain_prefix" value="" >
  </div>
  <div class="form-group">
    <label for="host_port">跳转端口</label>
    <input type="text" class="form-control" id="host_port" value="">
  </div>

  <button id="settings_saving_button" type="button" class="btn btn-default">保存</button>
</form>


<script>

$(document).ready(function() {
	loadAppSettings();
	onClickSettingsSavingButton();
	
	$("#app_selector").change(function() {
		loadAppSettings();
	});

});




function loadAppSettings() {
	var appId = $("#app_selector").val();
	
	$.ajax( {    
		    url:'../app/info', 
		    data:{ 
		    	appId: appId
		    },    
		    type:'get',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 $("#domain_prefix").val(data.domainPrefix);
		    	 $("#host_port").val(data.port);
		     },    
		     error : function() {      
		          alert("异常！");    
		     }    
	}); 	
}


function onClickSettingsSavingButton() {

	$("#settings_saving_button").click(function() {
		var appId = $("#app_selector").val();
		var port = $("#host_port").val();
		var domainPrefix = $("#domain_prefix").val();
	
		$.ajax( {    
		    url:'../app/update', 
		    data:{ 
		    	appId: appId,
		    	port: port,
		    	domainPrefix: domainPrefix
		    },    
		    type:'put',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if (data.code == 200) {
		    		alert("保存成功！");
		    	} else {
		    		alert(data.errorMsg);
		    	}
		     },    
		     error : function() {      
		          alert("异常！");    
		     }    
		}); 	
	
	
	});

}

</script>