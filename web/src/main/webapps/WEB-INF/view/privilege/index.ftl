

<div>
	<button class="btn btn-primary" id="create_privilege_modal_button" type="button">新建权限</button>

	<br /><br /><br />
	
	<table id="privileges_table"></table>
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

});


function initPrivilegesTable(columns) {

	$("#privileges_table").bootstrapTable({
    	url: '../user/list',
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


function refreshPrivilegesTable() {



}




</script>