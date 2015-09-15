<#import "/spring.ftl" as spring/>
<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="deliveryList">
    <table class="table table-hover table-bordered">
    <thead>
            <tr>
              <th>订单号</th>
              <th>金额</th>
              <th>收货人</th>
              <th>详细地址</th>
              <th>下单时间</th>
              <th>申请退货时间</th>
              <th>订单状态</th>
              <th>操作</th>
            </tr>
    </thead>
    <tbody>
    		<#list orders  as order>
            <tr>
              <td>${order.taoOrderSn}</td>
              <td>${order.goodsAmount}</td>
              <td><#if order.deliveryInfo?exists>${order.deliveryInfo.consignee}</#if></td>
              <td><#if order.deliveryInfo?exists>${order.deliveryInfo.address}</#if></td>
              <td>${order.createTime}</td>
              <td><#if order.cancelTime?exists>${order.cancelTime}</#if></td>
              <td>${order.goodsReturnStatus}</td>
              <td><button href="#" data-toggle="modal" data-target="#returnModal" data-order-sn="${order.taoOrderSn}" class="btn btn-primary returnBtn">退货入库</button></td>
            </tr>
            </#list>
          </tbody>
    </table>
</div>

<div class="modal fade" id="returnModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               退货商品
            </h4>
         </div>
         <div class="modal-body">
                <table class="table table-hover table-bordered">
				    <thead>
				            <tr>
				              <th>商品名称</th>
				              <th>数量</th>
				              <th>购买价</th>
				              <th>入库数量</th>
				            </tr>
				    </thead>
				    <tbody class="tbody">
				   </tbody>
			  </table>
         </div>
         <div class="modal-footer">
          	<button type="button" class="btn btn-primary submitReturn">
               退货入库
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
               取消
            </button>
         </div>
      </div>
  </div>
</div>
<script>
	var orderDetailMap = {};
	window.onload=function(){
		<#list orders as order>
			orderDetailMap["${order.taoOrderSn}"] = [];
			<#list order.specList as spec>
				var item = {};
				item["specId"] = "${spec.specId}";
				item["num"] = "${spec.buyNum}";
				item["name"] =  <#if spec.purchasePrice?exists>"${spec.specName}"<#else>"no name"</#if>;
				item["price"] = <#if spec.purchasePrice?exists>"${spec.purchasePrice}"<#else>"no price"</#if>;
				orderDetailMap["${order.taoOrderSn}"].push(item);
			</#list>
		</#list>
	}
</script>
<script src="<@spring.url '/assets/js/common.js' />"></script>
<script src="<@spring.url '/assets/js/toBeReturnedList.js' />"></script>
</@base.page>