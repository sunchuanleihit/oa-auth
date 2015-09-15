var $operator = '<td> '+
				'    <div class="form-inline  C">'+
				'<button type="button" class="btn-sm btn btn-default pull-left" onclick="reduce_num()">-</button>'+
				' <div class="form-group span1" style="margin-left:10px;float: left">'+
				'    <input type="text" value="0" class="tac span1 pull-left edit_num" placeholder="0" style="margin:0;line-height: 24px;" max_stock="9999" specid="1764" goodsid="">'+
				'  </div>'+
				'<button type="button" class="btn-sm btn btn-primary pull-left" style="margin-left:10px" onclick="add_num()">+</button>'+
				'</div>'+
				'</td></tr>';

var addedItems = [];
$('.addBtn').click(function(){
	var specId = $('specId').val();
	if(specId === "")
	{
		alert("请输入商品条码");
		return false;
	}
	sspHttpPost("goods",{keyWords:specId},function(data){
		if(data.length === 0)
		{
			alert("本仓无此商品");
			return false;
		}
		var item = data[0];
		if(addedItems.indexOf(item.specId) !== -1)
		{
			alert("该商品已在退货列表中");
			return false;
		}
		addedItems.push(item.specId);
		var $item = '<tr>' + 
		  			'<td>' + item.specName + '</td>' +
		  			'<td>' + item.bn + '</td>' +
		  			'<td id="stock">' + item.stock + '</td>' +
		  			'<td>' + item.purchasePrice + '</td>' +
		  			'<td>' + item.sellPrice + '</td>' ;
		$(".tbody").append($item + $operator);
	});
});

function add_num(){
	var stock = $("#stock").val();
	var editNum = $('.edit_num').val();
	if(editNum >= stock)
	{
		alert("超过最大库存量");
		return false;
	}
	$('.edit_num').val(editNum++);
}
function reduce_num(){
	var editNum = $('.edit_num').val();
	if(editNum <= 0)
	{
		alert("不能再少了");
		return false;
	}
	$('.edit_num').val(editNum--);
}