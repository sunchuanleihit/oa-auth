


<label>系统用户</label>

<table id="usersRoleTable"></table>




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
		
		
	initUsersRoleTable(columns);
	onAppSelectorChange();
});


function onAppSelectorChange() {
	$("#app_selector").change(function() {
		refreshUsersRoleTable();
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


</script>
