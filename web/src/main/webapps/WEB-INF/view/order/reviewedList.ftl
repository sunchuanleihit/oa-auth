<#import "/spring.ftl" as spring/>
<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="btn-group" role="group">
         <button class="btn btn-default sendAll"  data-toggle="modal" data-target="#myModal">批量发货</button>
         <button class="btn btn-default">批量打单</button>
</div>

<br><br>

<div class="deliveryList">
    <table class="table table-hover table-bordered">
    <thead>
            <tr>
              <th><input type="checkbox" class="checkAll">全选</th>
              <th>订单号</th>
              <th>金额</th>
              <th>收货人</th>
              <th>联系电话</th>
              <th>收货地址</th>
              <th>下单时间</th>
              <th>期望送达时间</th>
              <th>操作</th>
            </tr>
    </thead>
    <tbody>
    <#list orders  as order>
            <tr>
              <th scope="row"><input type="checkbox" class="checkItem" data-order-sn="${order.taoOrderSn}"></th>
              <td>${order.taoOrderSn}</td>
              <td>${order.goodsAmount}</td>
               <td><#if order.deliveryInfo?exists>${order.deliveryInfo.consignee}</#if></td>
                <td><#if order.deliveryInfo?exists>${order.deliveryInfo.tel}</#if></td>
                <td><#if order.deliveryInfo?exists>${order.deliveryInfo.address}</#if></td>
              <td>${order.createTime}</td>
              <td><#if order.deliveryInfo?exists>${order.deliveryInfo.needShippingTime}</#if></td>
              <td>
              <button type="button" class="btn btn-primary sendOrder" onclick="" data-order-sn=${order.taoOrderSn} data-toggle="modal" data-target="#myModal" >发货</button>
              <button type="button" class="btn btn-info" onclick="" >打单</button>
              <button type="button" class="btn btn-danger refuseOrderItem" onclick="" data-order-sn="${order.taoOrderSn}" data-toggle="modal" data-target="#refuseModal">拒绝订单</button>
              </td>
            </tr>
    </#list>
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
           <#list senders  as sender>
	            <div class="radio">
	              <label>
	                <input type="radio" name="senderRadios" value="${sender.dId}" <#if sender_index ==0>checked</#if>>
	                ${sender.name}, ${sender.phone}
	              </label>
	            </div>
	        </#list>
	        </div>
         </div>
         <div class="modal-footer" style="text-align:center">
                <button type="button" class="btn btn-primary submitSendOrder">
                   确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                   取消
                </button>
         </div> 
      </div>
  </div>
</div>

<div class="modal fade" id="refuseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
           <div class="refuseReason-container">
            <#list refuseReasons  as refuseReason>
	            <div class="radio">
	              <label>
	                <input type="radio" name="refuseReasonRadios" value="${refuseReason.id}" <#if refuseReason_index ==0>checked</#if>>
	                ${refuseReason.value}
	              </label>
	            </div>
	        </#list>
	        </div>
         </div>
         <div class="modal-footer" style="text-align:center">
                <button type="button" class="btn btn-primary refuseOrder">
                   确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                   取消
                </button>
         </div> 
      </div>
  </div>
</div>

<script src="<@spring.url '/assets/js/common.js' />"></script>
<script src="<@spring.url '/assets/js/reviewedList.js' />"></script>
</@base.page>