<button class="btn btn-primary" id="add_user_role_modal_button" type="button">添加系统用户</button>

<br /><br />

<table id="usersRoleTable"></table>


<!--        添加系统用户  模态框          -->
<div class="modal fade" id="add_user_role_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">添加系统用户</h4>
      </div>
      <div class="modal-body">
      	
      	<div id="all_roles_panel">
			<span>系统角色</span>
			<div id="all_roles">
			</div>
      	</div>

		
		<div id="all_users_panel">
		
			<span>非系统用户</span>
			<div id="all_users">
				<table id="users_not_in_app_table"></table>
			</div>
		</div>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="add_user_role_button" class="btn btn-primary">添加</button>
      </div>
    </div>
  </div>
</div>




<script type="text/javascript">

$(document).ready(function() {
	
	var columns = [ 
			{
		        field: 'name',
		        title: '名字'
		    }, {
		        field: 'email',
		        title: '邮箱'
		    }, {
		        field: 'department',
		        title: '部门'
		    }, {
		    	field: 'roles',
		    	title: '角色',
		    	formatter: roleFormatter
		    }
		];
		
	var modalColumns = [ 
	        {
	        	field: 'id',
	        	title: 'id'
	        },
			{
		        field: 'name',
		        title: '名字'
		    }, {
		        field: 'email',
		        title: '邮箱'
		    }, {
		        field: 'department',
		        title: '部门'
		    }, {
		    	checkbox: true
		    }
		];
		
		
	initUsersRoleTable(columns);
	initUsersNotInAppTable(modalColumns);
	onAppSelectorChange();
	onClickAddUserRoleModal();
	updateAllRoles();
});


function onAppSelectorChange() {
	$("#app_selector").change(function() {
		refreshUsersRoleTable();
		updateAllRoles();
		refreshUsersNotInAppTable();
	});
}


function initUsersRoleTable(columns) {
	var appId = $("#app_selector").val();

	$("#usersRoleTable").bootstrapTable({
    	url: '../user/listWithRole?appId=' + appId,
	    columns: columns,
	    pagination : true,
		sidePagination: 'server',
		pageNumber: 1,
		pageSize: 15,
		icons: {
			refresh: 'glyphicon-refresh',
		}
	});
}

function refreshUsersRoleTable() {
	var appId = $("#app_selector").val();
	$("#usersRoleTable").bootstrapTable('refresh', {url: '../user/listWithRole?appId=' + appId});
}


function roleFormatter(value, row, index) {
	var roleList = [];
	
	for (var idx in value) {
		var roleName = value[idx].name;
		roleList.push(roleName);
	}
	
	return roleList.join(",  ");
}



function onClickAddUserRoleModal() {

	$("#add_user_role_modal_button").click(function() {
		$("#add_user_role_modal").modal("show");
	});
	
	$("#add_user_role_button").click(function() {
		var roleIdList = [];
		var userIdList = [];
		var appId = $("#app_selector").val();
	
		$(".add_user_role_checkbox").each(function() {
			if ($(this).is(":checked")) {
				var roleId = $(this).attr("role_id");
				roleIdList.push(roleId);
			}
		});
		
		var users = $('#users_not_in_app_table').bootstrapTable('getSelections');
		for (var idx in users) {
			var user = users[idx];
			userIdList.push(user.id);
		}
		
		if (roleIdList.length <= 0) {
			alert("请勾选至少一个角色！");
			return;
		}
		if (userIdList.length <= 0) {
			alert("请选择至少一个用户！");
			return;
		}
		
		$.ajax( {    
		    url:'../user/addUserRoleForApp', 
		    data:{ 
		    	appId: appId,
		    	userId: userIdList,
		    	roleId: roleIdList
		    },    
		    type:'put',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	alert("创建成功！");
		    	 	refreshUsersRoleTable();
					refreshUsersNotInAppTable();
		    	 	
		    	 } else {
		    	 	alert("创建失败！");
		    	 }
		    },    
		    error : function() {      
		          alert("异常！");    
		    }    
		});
		
		
	});

}


function updateAllRoles() {

	var appId = $("#app_selector").val();
	
	$.ajax( {    
	    url:'../role/rolesByAppId', 
	    data:{ 
	    	appid:appId 
	    },    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	$("#all_roles").html("");
	    	
	    	for (var idx in data) {
	    	 	var role = data[idx];
	    	 	$("#all_roles").append("<input type='checkbox' class='add_user_role_checkbox' role_id='"+role.id+"' /> " + role.name);
	    	}
	    },    
	    error : function() {      
	          alert("异常！");    
	    }    
	}); 

}



function initUsersNotInAppTable(columns) {
	var appId = $("#app_selector").val();
	
	$("#users_not_in_app_table").bootstrapTable({
    	url: '../user/listNotInApp?appId=' + appId,
	    columns: columns,
	    pagination : true,
		sidePagination: 'server',
		pageNumber: 1,
		pageSize: 15,
		icons: {
			refresh: 'glyphicon-refresh',
		}
	});
}

function refreshUsersNotInAppTable() {
	var appId = $("#app_selector").val();
	
	$("#users_not_in_app_table").bootstrapTable("refresh", {url: '../user/listNotInApp?appId=' + appId});

}




</script>
