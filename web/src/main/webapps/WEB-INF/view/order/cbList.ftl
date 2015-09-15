<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div>
<table class="table">
 <tr>
    <th>订单号</th>
    <th>金额</th>
   <th>收货人</th>
    <th>详细地址</th>
    <th>下单时间</th>
    <th>操作</>
  </tr>
<#list orders as order>
  <tr>
    <td>${order.taoOrderSn}</td>
    <td>${order.goodsAmount}</td>
    <td><#if order.deliveryInfo?exists>${order.deliveryInfo.consignee}</#if></td>
     <td><#if order.deliveryInfo?exists>${order.deliveryInfo.address}</#if></td>
     <td>${order.createTime}</td>
     <td><a href="/order/confirmBooking">确认收货</a></td>
  </tr>
 </#list>
</table>
</div>

</@base.page>