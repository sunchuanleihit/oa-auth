

<label>全部用户</label>

<table id="usersTable"></table>




<script type="text/javascript">

$(document).ready(function() {
	
	var columns = [
			{
		        field: 'id',
		        title: 'id'
		    }, {
		        field: 'name',
		        title: '名字'
		    }, {
		        field: 'email',
		        title: '邮箱'
		    }, {
		        field: 'department',
		        title: '部门'
		    }
		];
		
		
	initUsersTable(columns);
	
});


function initUsersTable(columns) {
	$("#usersTable").bootstrapTable({
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



</script>