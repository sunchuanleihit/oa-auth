<#import "/common/base.ftl" as base/>
<#import "/spring.ftl" as spring />
<@base.page  "楼口商家进销系统">

<div class="row">
       
      <div class="col-md-1 btn-group">
		  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		   ${orderStatusMap[RequestParameters['status']!""] !"订单状态"}    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu">
		  <#list orderStatusMap?keys as key>
		    <li><a href="<@spring.url '/order/list?status=${key}' />">${orderStatusMap[key]}</a></li>
		  </#list>
		  </ul>
	  </div>
	    <div class="col-md-4">
		    <form action="<@spring.url '/order/searchBySn' />" id="search" class="form-inline" method="POST">
			    <div class="form-group">
			      	<input type="text" class="form-control searchInput" placeholder="订单号" name="taoOrderSn">
			        <button class="btn btn-primary searchBtn" type="button" onclick=search()>搜索</button>
			    </div>
		    </form>
	    </div>
    
</div>

<br/>

<div class="orderList">
	<table class="table table-hover table-bordered">
	<thead>
	        <tr>
	          <th>订单号</th>
	          <th>详细地址</th>
	          <th>收货人</th>
	          <th>手机</th>
	          <th>下单时间</th>
	          <th>总金额</th>
	          <th>订单状态</th>
	          <th>付款状态</th>
	          <th>操作</th>
	        </tr>
	</thead>
	<tbody>
        <#list orders as order>
	        <tr>
	          <td><#if order.deliveryInfo?exists>${order.taoOrderSn}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.deliveryInfo.address}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.deliveryInfo.consignee}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.deliveryInfo.tel}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.createTime}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.goodsAmount}</#if></td>
	          <td><#if order.deliveryInfo?exists>${order.orderStatus}</#if></td>
	          <td>已付款</td>
	          <td><a href="#" data-toggle="modal" data-target="#myModal" onclick=getInfo(<#if order.deliveryInfo?exists>${order.taoOrderSn}</#if>) >详情</a></td>
	        </tr>
	        </#list>
	      </tbody>
	</table>
</div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content" style="height:400px;">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               订单详情
            </h4>
         </div>
         <div class="modal-body">
         	<div class="table-container">
                <table class="table table-hover">
				    <thead>
				            <tr>
				              <th>商品名称</th>
				              <th>数量</th>
				              <th>购买价</th>
				              <th>优惠价</th>
				            </tr>
				    </thead>
				    <tbody class="tbody">
				            <tr>
				              <td>商品名称</td>
				              <td>数量</td>
				              <td>购买价</td>
				              <td>优惠价</td>
				            </tr>
				   </tbody>
			   </table>
			</div>
			
			   <hr class="divider">
			   <div class="payInfo pull-right">
			   		<p><b class="createTime"></b> 下单 <b class="consignee"></b></p>
			   		<p><b class="payTime"></b> 支付 </p>
			   		<p><b class="finishTime"></b> 自动审单 SYSTEM</p>
			   </div>
         </div>
         
      </div>
  </div>
</div>

<script src="<@spring.url '/assets/js/orderList.js' />"></script>
<script src="<@spring.url '/assets/js/common.js' />"></script>
</@base.page>