<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">

<form class="col-md-4" method="post">
      <div class="form-group">
        <label for="orginPwd">原始密码</label>
        <input type="password" class="form-control" id="orginPwd" placeholder="" name="oldPass">
      </div>

      <div class="form-group">
        <label for="newPwd">新密码</label>
        <input type="password" class="form-control" id="newPwd" placeholder="" name="newPass">
      </div>

      <div class="form-group">
        <label for="confirmPwd">确认密码</label>
        <input type="password" class="form-control" id="confirmPwd">
      </div>
      
      <br>

      <button type="submit" class="btn btn-default">提交修改</button>
</form>

</@base.page>