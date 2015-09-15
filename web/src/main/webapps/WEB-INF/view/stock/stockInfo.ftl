<#import "/spring.ftl" as spring/>
<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
 <div class="row" style="margin-right:0px;">
    <div class="col-md-2 ">
        <input type="text" placeholder="输入商品条码或货号" class="form-control specId">
    </div>
    <div class="col-md-2 ">
        <input type="text" placeholder="输入商品名称" class="form-control goodsName">
    </div>
    <div class="col-md-2">
      <select class="form-control goodsType">
        <option>商品分类</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
      </select>
    </div>
    <button class="btn searchBtn">查询</button>
</div>
<br>
<div class="orderList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <th>商品名称</th>
                  <th>条码</th>
                  <th>现有库存</th>   
                  <th>总仓库存</th>
                  <th>冻结库存</th>
                  <th>售价</th>
                  <th>采购在途数量</th>       
                  <th>操作</th>
                </tr>
        </thead>
        <tbody>
        <#list result.specList  as item>
                <tr>
                  <td>${item.specName}</td>
                  <td>${item.specId}</td>
                  <td>${item.storeStock}</td>   
                  <td>${item.stock}</td>
                  <td>${item.storeFreezStock}</td>
                  <td>${item.sellPrice}</td>
                  <td>${item.deliveryNum}</td>  
                  <td>
                    <div class="form-inline  C">
                        <a href="#" class="moreInStock" data-spec-id=${item.specId} data-toggle="modal" data-target="#myModal">盘盈入库</a>
                        |
                        <a href="#" class="lessInStock" data-spec-id=${item.specId} data-toggle="modal" data-target="#myModal">盘亏出库</a>
                    </div>
                  </td>
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
               盘盈入库
            </h4>
         </div>
         <div class="modal-body">
            <form class="" style="height:255px">
                <div class="row clearMargin">
                    <label for="goodName"  class="pull-left">商品名称:</label> 
                    <div class=" col-md-5">
                    	<span id = "goodName">伊利利乐枕纯牛奶240ml*16/箱 </span>
                    </div>
                </div>
                <br>
                <div class="row clearMargin">
                    <label for="bandId" class="pull-left">条码:</label>
                    <div class=" col-md-3">
                    	<span id = "bandId">6907992504070</span>
                    </div>
                </div>
                <br>
                <div class="row clearMargin">
                    <label for="remainNum" class="pull-left">现有库存:</label>
                    <div class=" col-md-3">
                    	<span id = "remainNum">33 </span>
                    </div>
                </div>
                <br>
                <div class="row clearMargin">
                    <label for="goodAmount" class="pull-left">*数量:</label>
                    <div class=" col-md-3">
                    	<input type="text" id="goodAmount" class="form-control">
                    </div>
                </div>
                <br>
                <div class="row clearMargin">
                    <label for="returnReason" class="pull-left">*原因:</label>
                    <div class=" col-md-5">
                    	<input type="text" id="returnReason" class="form-control">
                    </div>
                </div>
                <br>
                <div class="row align-center">
		                <button type="submit" class="btn btn-primary submitCheck">
		                   确定
		                </button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">
		                   取消
		                </button>
	            </div>
            </form>
         </div>
       
      </div>
  </div>
</div>
<script src="<@spring.url '/assets/js/stockInfo.js' />"></script>
</@base.page>