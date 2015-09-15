<#import "/common/base.ftl" as base/>
<@base.page  "楼口商家进销系统">
 <div class="row clearMargin">
    <div class="col-md-4">
	 	时间段
	    <input type="text" class="datepicker">
	    至
		<input type="text" class="datepicker">
	</div>
	<div class="col-md-2">
      <select class="form-control">
        <option>订单类型</option>
        <option>楼口订单</option>
        <option>生鲜订单</option>
        <option>预售订单</option>
      </select>
    </div>
    <button class="btn searchBtn">搜索</button>
</div>
<br>
<div class="topList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <td>日期</td>
                  <td>总订单量（笔）</td>
                  <td>单价（元）</td>
                  <td>销售金额（元）</td>
                </tr>
        </thead>
        <tbody>
            <tr>
              <th>20150307</th>
              <th>22</th>
              <th>11.3</th>
              <th>12.3</th>
            </tr>
        </tbody>
    </table>
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