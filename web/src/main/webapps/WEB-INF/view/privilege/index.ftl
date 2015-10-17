

<div>
	<button class="btn btn-primary" id="create_privilege_modal_button" type="button">新建权限</button>

	<br /><br /><br />
	
	<table id="privileges_table"></table>
</div>



<!--     新增角色模态框       -->
<div class="modal fade" id="create_privilege_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">新建权限</h4>
      </div>
      <div class="modal-body">
			<form class="form-horizontal">
			  <div class="form-group">
			    <label for="priv_name"  class="col-sm-2 control-label">权限名</label>
			    <div class="col-sm-10">
			      <input type="text"  class="form-control" id="priv_name" value="" >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="priv_key"  class="col-sm-2 control-label">权限标识</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="priv_key" value="" >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="priv_type"  class="col-sm-2 control-label">类别</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="priv_type" value="" >
			    </div>
			  </div>	  
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="create_priv_button" class="btn btn-primary">新建</button>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">

$(document).ready(function() {
	var privColumns = [
		{
			field: "name",
			title: "权限名"
		},
		{
			field: "privKey",
			title: "权限标识"
		},
		{
			field: "type",
			title: "类型"
		},
	];
	
	initPrivilegesTable(privColumns);
	onClickCreatePrivilegeModalButton();
	onClickCreatePrivButton();

});


function initPrivilegesTable(columns) {
	var appId = $("#app_selector").val();

	$("#privileges_table").bootstrapTable({
    	url: '../privilege/listByApp?appid=' + appId,
	    columns: columns,
	    pagination : true,
		sidePagination: 'server',
		pageNumber: 1,
		pageSize: 15,
		icons: {
			refresh: 'glyphicon-refresh',
		}
	});
	
	onAppSelectorChange();

}

function onAppSelectorChange() {
	$("#app_selector").change(function() {
		refreshPrivilegesTable()
	});
}


function refreshPrivilegesTable() {
	var appId = $("#app_selector").val();
	$("#privileges_table").bootstrapTable('refresh', {url: '../privilege/listByApp?appid=' + appId});
}

function onClickCreatePrivilegeModalButton() {
	$("#create_privilege_modal_button").click(function() {
		$("#create_privilege_modal").modal("show");
	});
}

function onClickCreatePrivButton() {
	$("#create_priv_button").click(function() {
		var privName = $("#priv_name").val();
		var privKey = $("#priv_key").val();
		var privType = $("#priv_type").val();
		var appId = $("#app_selector").val();
	
		$.ajax( {    
		    url:'../privilege/create', 
		    data:{ 
		    	privName: privName,
		    	privKey: privKey,
		    	privType: privType,
		    	appId: appId
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	alert("新建成功！");
		    	 	$("#create_privilege_modal").modal("hide");
		    	 	refreshPrivilegesTable();
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