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
        <option>商品分类</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
      </select>
    </div>
    <button class="btn searchBtn">搜索</button>
</div>
<br>
<div class="topList">
    <table class="table table-hover table-bordered">
        <thead>
                <tr>
                  <th>排名</th>
                  <th>商品名称</th>
                  <th>销售数量</th>
                  <th>所属分类</th>
                </tr>
        </thead>
        <tbody>
            <tr>
              <td>1</td>
              <td>15444342345634</td>
              <td>北大街xxxxxxx</td>
              <td>朱国珍</td>
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