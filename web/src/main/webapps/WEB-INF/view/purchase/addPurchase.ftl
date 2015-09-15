<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
   <div class="row" style="margin-right:0px;">
    <div class="col-md-2 ">
        <input type="text" class="pull-left form-control" placeholder="输入商品条码">
    </div>
    <div class="col-md-2 ">
        <input type="text" class="pull-left form-control" placeholder="输入商品名称">
    </div>
    <div class="col-md-2 ">
        <div class="btn-group">
          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            商品分类<span class="caret"></span>
          </button>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </div>
    </div>
    <button class="btn pull-left">查询</button>
    <button class="btn pull-right">前往下单</button>
</div>

<br>

<h4><span>可用余额：30000元 </span>  | <span>待采购商品金额：10000元</span></h4>

<br>

<table class="table table-hover table-bordered">
    <thead>
            <tr>
              <th>商品名称</th>
              <th>条码</th>
              <th>规格说明</th>
              <th>楼口库存</th>
              <th>现有库存</th>
              <th>采购中数量</th>
              <th>昨日销量</th>
              <th>最大可采数量</th>
              <th>进货价</th>
              <th>销售价</th>
              <th>操作</th>
            </tr>
    </thead>
    <tbody>
      <#list result.specList as spec>
            <tr>
              <td>${spec.goodsInfo.goodsName}</td>
              <td>${spec.bn}</td>
              <td>${spec.specName}</td>
              <td>${spec.stock}</td>
              <td>${spec.storeStock}</td>
              <td>${spec.deliveryNum}</td>
              <td>${spec.yesterdaySellNum}</td>
              <td>${spec.maxPurchaseNum}</td>
              <td>${spec.purchasePrice}</td>
              <td>${spec.sellPrice}</td>
              <td>
                <div class="form-inline  C">
                        <button type="button" class="btn-sm btn btn-default pull-left" onclick="reduce_num(${spec.specId},true)">-</button>
                          <div class="form-group span1" style="margin-left:10px;float: left">
                            <input type="text" value="0" class="tac span1 pull-left edit_num" placeholder="0" style="margin:0;line-height: 24px;" max_stock="${spec.maxPurchaseNum}" specid="${spec.specId}" goodsid="">
                          </div>
                        <button type="button" class="btn-sm btn btn-primary pull-left" style="margin-left:10px" onclick="add_num(${spec.specId})">+</button>
                </div>
              </td>
            </tr>
        </#list>
    </tbody>
</table>
<div class="">
    <nav>
      <ul class="pagination">
        <li>
          <a href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        
        <li><a href="#">${result.pageNum}</a></li>
        <li>
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
</div>
</@base.page>