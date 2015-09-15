<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="row">
    <div class="col-md-3 ">
        <input type="text" placeholder="扫描或输入物流单号" class="form-control">
    </div>

    <button class="btn">确认到货</button>
</div>
<br>
<div class="orderList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <th>采购单号</th>
                  <th>物流单号</th>
                  <th>下单时间</th>
                  <th>采购单状态</th>
                  <th>商品总价</th>
                  <th>操作</th>
                </tr>
        </thead>
        <tbody>
        <#list result.orderList ad order>
            <tr>
              <td>${order.orderNo}</td>
              <td>${order.shippingNo}</td>
              <td>${order.createTime}</td>
              <#if order.status==1>
              <td>待出库</td>
              <#elseif order.status==4>
              <td>待收货</td>
              <#/if>
              <td>${order.goodsAmount}</td>
              <td><a href="#" data-toggle="modal" data-target="#myModal">确认收货</a></td>
            </tr>
           </#list>
        </tbody>
    </table>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content" style="height:400px;">
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
            <div class="table-container" style="height:245px">
                <table class="table table-hover">
                    <thead>
                            <tr>
                              <th>商品名称</th>
                              <th>商品条码</th>
                              <th>价格</th>
                              <th>采购数量</th>
                              <th>出库数量</th>
                              <th>实际数量</th>
                            </tr>
                    </thead>
                    <tbody>
                            <tr>
                              <td>商品名称</td>
                              <td>商品条码</td>
                              <td>价格</td>
                              <td>采购数量</td>
                              <td>出库数量</td>
                              <td><input type="text"></td>
                            </tr>
                   </tbody>
               </table>
            </div>
         </div>
         <div class="modal-footer" style="text-align:center">
                <button type="button" class="btn btn-primary">
                   确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                   返回
                </button>
         </div> 
         
      </div>
  </div>
</div>
</@base.page>