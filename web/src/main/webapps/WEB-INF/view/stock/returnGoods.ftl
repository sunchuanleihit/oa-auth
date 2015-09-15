<#import "/spring.ftl" as spring/>
<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="row" style="margin-right:0px;">
    <div class="col-md-3 ">
        <input type="text" placeholder="输入或扫描商品条码" class="form-control specId">
    </div>

    <button class="btn addBtn">添加</button>
 
                <button class="btn pull-right">发起退货</button>
</div>
<br>
<div class="orderList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <th>商品名称</th>
                  <th>条码</th>
                  <th>现有库存</th>   
                  <th>进货价</th>
                  <th>销售价</th>       
                  <th>操作</th>
                </tr>
        </thead>
        <tbody class="tbody">
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
            <div class="table-container" style="height:255px">
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
<script src="<@spring.url '/assets/js/returnGoods.js' />"></script>
</@base.page>