<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">

<h4><span>可用余额：30000元 </span>  | <span>待采购商品金额：10000元</span></h4>

<br>

<table class="table table-hover table-bordered">
	<thead>
	        <tr>
	          <th>商品名称</th>
	          <th>条码</th>
	          <th>进货价</th>       
	          <th>规格说明</th>
	          <th>楼口库存</th>
	          <th>现有库存</th>
	          <th>采购中数量</th>
	          <th>操作</th>
	        </tr>
	</thead>
	<tbody>
	        <tr>
	          <th>商品名称</td>
	          <td>条码</td>
	          <td>进货价</td>
	          <td>规格说明</td>
	          <td>楼口库存</td>
	          <td>现有库存</td>
	          <td>采购中数量</td>     
	          <td>
	            <div class="form-inline  C">
	                    <button type="button" class="btn-sm btn btn-default pull-left" onclick="reduce_num(1764,true)">-</button>
	                      <div class="form-group span1" style="margin-left:10px;float: left">
	                        <input type="text" value="3" class="tac span1 pull-left edit_num" placeholder="0" style="margin:0;line-height: 24px;" max_stock="9999" specid="1764" goodsid="">
	                      </div>
	                    <button type="button" class="btn-sm btn btn-primary pull-left" style="margin-left:10px" onclick="add_num(1764)">+</button>
	            </div>
	          </td>
	        </tr>
	</tbody>
</table>

<div class="row clearMargin">
	<div class="order_notes ">
	    <label for="notes-text">备注说明： </label>
	    <input id=“notes-text” type="text">
	</div>
	<div class="pull-right">
	    <label for="notes-text">总计金额: </label> <span >8888元</span>
	</div>
</div>

<br>

<div class="row clearMargin">
        <button class=" btn pull-right">确认采购</button>  <label class="pull-right money-notes">单笔采购金额需要大于500元</label>
</div>

</@base.page>