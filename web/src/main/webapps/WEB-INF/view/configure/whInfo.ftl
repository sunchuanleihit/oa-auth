<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">

 <form action="" method="post">
    <div class="saleTime form-group">
        <p class="lead" style="margin-bottom:0px;">营业时间</p>
	    <input name="workSTime" type="text" class="form-control Wdate valid start_time" name="work_stime" value="07:00:00" style="width: 280px;height: 34px;" onclick="WdatePicker({isShowClear:false, dateFmt: 'HH:mm:ss', lang:'zh-cn'});" aria-required="true" aria-invalid="false" aria-describedby="work_stime-error">
	    至
	    <input  name="workETime"type="text" class="form-control Wdate valid end_time" name="work_etime" value="18:00:00" style="width: 280px;height: 34px;" onclick="WdatePicker({isShowClear:false, dateFmt: 'HH:mm:ss', lang:'zh-cn'});" aria-required="true" aria-invalid="false" aria-describedby="work_stime-error">
    </div>

    <div class="contactInfo form-group">
        <p class="lead" style="margin-bottom:0px;">联系电话</p>
        <input type="telephone" name="phone" value=${phone}>
    </div>

    <br><br>
    
    <button type="submit" class="btn btn-default">提交修改</button>  
</form>

</@base.page>