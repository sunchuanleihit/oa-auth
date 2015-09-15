<#import "/common/base.ftl" as base/>
<#import "/spring.ftl" as spring/>
<@base.page  "楼口商家进销系统">

<div class="addNewDelivery">
	<div class="row">
	<form id="addForm" method="POST" action="<@spring.url '/configure/addSender' /> ">
	  <div class="col-lg-3">
	    <div class="input-group">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button">姓名</button>
	      </span>
	      <input type="text" class="form-control" placeholder="用户名" name="dName">
	    </div>
	  </div>
	  <div class="col-lg-3">
	    <div class="input-group">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button">手机</button>
	      </span>
	      <input type="text" class="form-control" placeholder="手机号" name="phone">
	    </div>
	  </div>
	  <div class="col-lg-3">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button" onclick="addSender()">添加</button>
	      </span>
	  </div>
	  </form>
</div>

<br/>
<div class="deliveryList">
	<table class="table table-hover table-bordered">
	<thead>
	        <tr>
	          <th>姓名</th>
	          <th>手机号</th>
	          <th>操作</th>
	        </tr>
	</thead>
	<tbody>
	<#list senders as sender>
		  <tr>
              <td>${sender.name}</td>
              <td>${sender.phone}</td>
              <td>
              <button type="button" class="btn btn-danger" onclick=del(this) id="${sender.dId}">禁用</button>
              <button type="button" class="btn btn-info" onclick=resetPass(this) id="${sender.dId}">重置密码</button>
              </td>
            </tr>
	</#list>
	      </tbody>
	</table>
</div>
<form id="senderOp" method="POST" style="display:none">
<input type="text"  name="dId" id="opInput" value="11" />
</form>
<script>
function addSender()
{
    $("#addForm").submit();
}
function resetPass(obj)
{
         $("#senderOp").attr("action","<@spring.url '/configure/resetDPass' />");
         $("#opInput").val(obj.getAttribute("id"));
         $("#senderOp").submit();
}
function del(obj)
{
    $("#senderOp").attr("action","<@spring.url '/configure/deleteSender' />");
    $("#opInput").val(obj.getAttribute("id"));
    $("#senderOp").submit();
}
</script>
</@base.page>