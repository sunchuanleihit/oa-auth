

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
			  
			  <div class="form-group">
			    <label for="user_init_password"  class="col-sm-2 control-label">初始密码</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="user_init_password" value="123456" >
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





<!--     删除用户模态框       -->
<div class="modal fade" id="delete_user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">删除用户</h4>
      </div>
      <div class="modal-body">

			<form class="form-horizontal">
				确定删除用户'<span id="deleting_username_label"></span>'吗?
			</form>
			<input type="hidden" id="deleting_user_id"  />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="delete_user_button" class="btn btn-primary">确定删除</button>
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
		    }, {
		        field: 'operation',
		        title: '操作',
		        formatter: userOperationFormatter
		    }
		];
		
		
	initUsersTable(columns);
	onClickCreateUserModal();
	onClickDeleteUserModal();
	onClickDeleteUserButton();
	
});


function initUsersTable(columns) {
	$("#usersTable").bootstrapTable({
    	url: '../user/list',
	    columns: columns,
	    pagination : true,
		sidePagination: 'server',
		pageNumber: 1,
		pageSize: 15,
		cache: false,
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
		var initPassword = $("#user_init_password").val();
		
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
		
		if (initPassword.match(blankPattern)) {
			alert("初始密码不得为空！");
			return;
		}
	
		$.ajax( {    
		    url:'../user/create', 
		    data:{ 
		    	name: userName,
		    	email: userEmail,
		    	department: userDepartment,
		    	password: initPassword
		    	
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	$("#create_user_modal").modal("hide");
		    	 	refreshUsersTable();
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



function userOperationFormatter(value, row, index) {
	return "<a user_id='"+row.id+"' user_name='"+row.name+"' class='delete_user_button_modal'>删除</a>";
}


function onClickDeleteUserModal() {
	$("#usersTable").on("click", ".delete_user_button_modal",function() {
		var userId = $(this).attr("user_id");
		var userName = $(this).attr("user_name");
		
		$("#deleting_username_label").html(userName);
		$("#deleting_user_id").val(userId);
		$("#delete_user_modal").modal("show");
	});
}

function onClickDeleteUserButton() {
	$("#delete_user_button").click(function() {
		var userId = $("#deleting_user_id").val();
	
		$.ajax( {    
		    url:'../user/entity?id=' + userId, 
		    data:{ 
		    },    
		    type:'delete',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	 if (data.code == 200) {
		    	 	$("#delete_user_modal").modal("hide");
		    	 	refreshUsersTable();
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