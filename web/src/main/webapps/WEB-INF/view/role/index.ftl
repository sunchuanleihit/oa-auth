

<div>
	
	<table id="roles_table"></table>

</div>



<script>

$(document).ready(function() {
	var rolesColumns = [
		{
	        field: 'id',
	        title: 'id'
	    }, {
	        field: 'desc',
	        title: '描述'
	    }, {
	        field: 'privilege',
	        title: '权限'
	    }, 
	    {
	        field: 'role',
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





</script>