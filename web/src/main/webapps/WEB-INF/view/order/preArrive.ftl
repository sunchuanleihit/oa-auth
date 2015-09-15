<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="row">
    <div class="col-md-3 ">
        <input type="text" placeholder="请输入物流单号" class="form-control">
    </div>

    <button class="btn">确认到货</button>
</div>
<br>
<div class="orderList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <th>订单号</th>
                  <th>物流单号</th>
                  <th>金额</th>
                  <th>收货人</th>
                  <th>详细地址</th>
                  <th>下单时间</th>
                  <th>操作</th>
                </tr>
        </thead>
        <tbody>
            <tr>
              <td>15444342345634</td>
              <td>15444342345634</td>
              <td>新北区</td>
              <td>北大街xxxxxxx</td>
              <td>朱国珍</td>
              <td>1544444</td>
              <td><a href="#" data-toggle="modal" data-target="#myModal">确认收货</a></td>
            </tr>
        </tbody>
    </table>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content" style="height:250px;">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               请选择送货员
            </h4>
         </div>
         <div class="modal-body">
           <div class="delivery-container">
	            <div class="radio">
	              <label>
	                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
	                张二 1538093993
	              </label>
	            </div>
	            
	            <div class="radio">
	              <label>
	                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option2">
	                张二 1538093993
	              </label>
	            </div>
	        
	        </div>
         </div>
         <div class="modal-footer" style="text-align:center">
                <button type="button" class="btn btn-primary">
                   确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                   取消
                </button>
         </div> 
         
      </div>
  </div>
</div>
</@base.page>