

<button class="btn btn-primary" id="create_user_modal_button" type="button">新建用户</button>

<br /><br /><br />

<table id="usersTable"></table>


<!--     新增用户模态框       -->
<div class="modal fade" id="create_user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">新建用户</h4>
      </div>
      <div class="modal-body">

			<form class="form-horizontal">
			  <div class="form-group">
			    <label for="user_email"  class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="text"  class="form-control" id="user_email" value="" >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="user_name"  class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="user_name" value="" >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="user_department"  class="col-sm-2 control-label">部门</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="user_department" value="" >
			    </div>
			  </div>
			  
			</form>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="create_user_button" class="btn btn-primary">新建</button>
      </div>
    </div>
  </div>
</div>




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
	onClickCreateUserModal();
	
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


function refreshUsersTable() {
	$("#usersTable").bootstrapTable('refresh', {});
}


function onClickCreateUserModal() {
	$("#create_user_modal_button").click(function() {
		$("#create_user_modal").modal("show");
	});
	
	$("#create_user_button").click(function() {
		var userEmail = $("#user_email").val();
		var userName = $("#user_name").val();
		var userDepartment = $("#user_department").val();
		
		var blankPattern = /^\s*$/;
		var emailPattern = /^[a-zA-Z0-9_-]+@loukou.com$/;
		
		if (!userEmail.match(emailPattern)) {
			alert("邮箱格式不正确,必须为@loukou.com！");
			return;
		}
		
		if (userName.match(blankPattern)) {
			alert("用户名不得为空！");
			return;
		}
		
		if (userDepartment.match(blankPattern)) {
			alert("用户部门不得为空！");
			return;
		}
	
		$.ajax( {    
		    url:'../user/create', 
		    data:{ 
		    	name: userName,
		    	email: userEmail,
		    	department: userDepartment
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	$("#create_user_modal").modal("hide");
		    	 	refreshUsersTable();
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









</script>