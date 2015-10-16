

<div>
	<button class="btn btn-primary" id="create_role_modal_button" type="button">新建角色</button>

	<br /><br /><br />
	
	<table id="roles_table"></table>

</div>




<!--     新增角色模态框       -->
<div class="modal fade" id="create_role_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">新建角色</h4>
      </div>
      <div class="modal-body">

			<form class="form-horizontal">
			  <div class="form-group">
			    <label for="role_name"  class="col-sm-2 control-label">角色名</label>
			    <div class="col-sm-10">
			      <input type="text"  class="form-control" id="role_name" value="" >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="role_key"  class="col-sm-2 control-label">标识</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="role_key" value="" >
			    </div>
			  </div>	
			  
			</form>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="create_role_button" class="btn btn-primary">新建</button>
      </div>
    </div>
  </div>
</div>




<script>

$(document).ready(function() {
	var rolesColumns = [
		{
	        field: 'id',
	        title: 'id'
	    }, {
	        field: 'name',
	        title: '角色名'
	    }, {
	        field: 'privilege',
	        title: '权限'
	    }, 
	    {
	        field: 'roleKey',
	        title: '标识'
	    }, 
	    {
	        field: 'roleOperation',
	        title: '操作',
	        formatter: roleOperationFormater
	    }
    ];
	
	initRolesTable(rolesColumns);
	onAppSelectorChange();
	onClickRoleEditButton();
	onClickCreateRoleModalButton();
	onClickCreateRoleButton();
	
});


function onAppSelectorChange() {
	$("#app_selector").change(function() {
		refreshRolesTable();
	});
}


function initRolesTable(rolesColumns) {
	var appid = $("#app_selector").val();

	$("#roles_table").bootstrapTable({
		url:"../role/rolesByAppId?appid=" + appid,
		columns:rolesColumns,
		
	});
}


function refreshRolesTable() {
	var appid = $("#app_selector").val();
	$("#roles_table").bootstrapTable('refresh',{url: "../role/rolesByAppId?appid=" + appid});
}


function roleOperationFormater(value, row, index) {
	var operationList = [];
	
	operationList.push("<a role_id='"+row.id+"' class='role_edit_button'>编辑</a>");
	
	return operationList.join("&nbsp;&nbsp;");
	
}

function onClickRoleEditButton() {
	$("#roles_table").on("click", ".role_edit_button", function() {
		var roleId = $(this).attr("role_id");
	
		pushPageStack("../role/detail?id=" + roleId);
	});
}

function onClickCreateRoleModalButton() {
	$("#create_role_modal_button").click(function() {
		$("#create_role_modal").modal("show");
	
	});
}

function onClickCreateRoleButton() {
	$("#create_role_button").click(function() {
	
		var name = $("#role_name").val();
		var key = $("#role_key").val();
		var appId = $("#app_selector").val();
		
		$.ajax( {    
		    url:'../role/create', 
		    data:{ 
		    	name: name,
		    	key: key,
		    	appId: appId
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	alert("新建成功！");
		    	 	$("#create_role_modal").modal("hide");
		    	 	refreshRolesTable();
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