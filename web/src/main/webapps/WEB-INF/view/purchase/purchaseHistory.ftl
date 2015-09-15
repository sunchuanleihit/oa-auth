<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
<div class="">
    <table class="table   table-bordered">
        <tbody>
            <tr>
                <th>采购单号</th>                       
                <th><b>15082711184121949044</b></th>
                <th>采购时间</th>
                <th><b>2015-08-27 11:18:42</b></th>
                <th colspan="2" rowspan="2" width="7%">
                    <a href="" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal">查看详情</a>
                </th>
            </tr>
            <tr class="alt-row">
                <th>订单状态</th>                       
                <th><b>已审核</b></th>
                <th>总价</th>
                <th>656.60</th>
            </tr>
        </tbody>
    </table>
    <br>
    <table class="table   table-bordered">
        <tbody>
            <tr>
                <th>采购单号</th>                       
                <th><b>15082711184121949044</b></th>
                <th>采购时间</th>
                <th><b>2015-08-27 11:18:42</b></th>
                <th colspan="2" rowspan="2" width="7%">
                    <a href="" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#myModal">查看详情</a>
                </th>
            </tr>
            <tr class="alt-row">
                <th>订单状态</th>                       
                <th><b>已审核</b></th>
                <th>总价</th>
                <th>656.60</th>
            </tr>
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
         	<div class="table-container" style="height:255px">
                <table class="table table-hover">
				    <thead>
				            <tr>
				              <th>商品名称</th>
				              <th>商品条码</th>
				              <th>价格</th>
				              <th>采购数量</th>
				              <th>出库数量</th>
				            </tr>
				    </thead>
				    <tbody>
				    		<tr>
				              <td>商品名称</td>
				              <td>商品条码</td>
				              <td>价格</td>
				              <td>采购数量</td>
				              <td>出库数量</td>
				            </tr>
				   </tbody>
			   </table>
			</div>
			<div class="modal-footer" style="text-align:center">
	          	<button type="button" class="btn btn-primary" data-dismiss="modal">
	               确定
	            </button>
            </div>
         </div>
         
      </div>
  </div>
</div>

<div class="">
    <nav>
      <ul class="pagination">
        <li>
          <a href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li>
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
</div>
</@base.page>