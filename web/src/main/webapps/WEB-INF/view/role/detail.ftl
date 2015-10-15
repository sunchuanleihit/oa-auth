<a class="back_button">返回</a>

<form class="form-horizontal">
  <div class="form-group">
    <label for="roleId"  class="col-sm-2 control-label">id</label>
    <div class="col-sm-10">
      <input type="text" readonly="readonly" class="form-control" id="roleId" value="${role.id}" >
    </div>
  </div>
  
  <div class="form-group">
    <label for="roleApp"  class="col-sm-2 control-label">所属系统</label>
    <div class="col-sm-10">
      <input type="text" readonly="readonly" class="form-control" id="roleApp" value="${role.appDesc}"  >
    </div>
  </div>
  
  <div class="form-group">
    <label for="roleDesc" class="col-sm-2 control-label">角色</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="roleName" value="${role.name}"  >
    </div>
  </div>
  
  <div class="form-group">
    <label for="roleDesc" class="col-sm-2 control-label">角色标识</label>
    <div class="col-sm-10">
      <input type="text" readonly="readonly" class="form-control" id="roleDesc"  value="${role.role}" >
    </div>
  </div>
  
  
  <div id="privileges_panel">
  	  <lable>角色权限</label>
  	  <div>
  	  	  <#if role.privileges??>
  	  	  	<#list role.privileges as privilege>
  	  	  		<span class="priv_check_span">
  	  	  			<input 
  	  	  				type="checkbox"
  	  	  				class="priv_check" 
  	  	  				<#if privilege.status == 1 >
  	  	  					checked="checked" 
  	  	  				</#if>
  	  	  			    value="${privilege.privKey}" />${privilege.desc}
  	  	  		</span>
  	  	  	</#list>
  	  	  </#if>
  	  </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="save_role_button" type="button" class="btn btn-default">保存</button>
    </div>
  </div>

</form>


<script type="text/javascript">

$(document).ready(function() {

	$(".back_button").click(function() {
		popPageStack();
	});
	
	onClickSaveRoleButton();

});


function onClickSaveRoleButton() {
	$("#save_role_button").click(function() {
		var id = $("#roleId").val();
		var name = $("#roleName").val();
		var privKeys = new Array();
		
		$(".priv_check").each(function() {
			if ($(this).is(":checked")) {
				privKeys.push($(this).val());
			}
		});

		
		$.ajax( {    
		    url:'../role/update', 
		    data:{ 
		    	id: id,
		    	name: name,
		    	privKeys: privKeys
		    },    
		    type:'put',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	alert("更新成功！");
		    	 	loadCurrentPage("../role/detail?id=" + id);
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






