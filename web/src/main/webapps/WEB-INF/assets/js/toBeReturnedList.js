var taoOrderSn = "";
$(".returnBtn").click(function(){
	taoOrderSn = this.dataset.orderSn;
	var items = orderDetailMap[taoOrderSn];
	$(".tbody").children().remove();
	$.each(items,function(i,e){
		$(".tbody").append("<tr "+"data-spec-id="+e.specId+">"+"<td>"+e.name+"</td>"+"<td>"
				+e.num+"</td>"+"<td>"+e.price+"</td>"+"<td><input type='text' class='returnNum' value='"+ e.num +"' /></td>"+"</tr>");
	});
});


$(".submitReturn").click(function(){
	var rows = $(".tbody").children();
	var specList = [];
	$.each(rows,function(i,e){
		var spec = {};
		spec["specId"]=e.dataset.specId;
		spec["confirmNum"]=$(e).find("td .returnNum").val();
		specList.push(spec);
	});
	var returnDetail = {};
	returnDetail["specList"] = specList;
	returnDetail["taoOrderSn"] = taoOrderSn;
	
	sspHttpPost("returnStorage",returnDetail,function(data){
		if(data.code===200)
		{
			$('#returnModal').modal('hide');
			alert("操作成功");
			window.location.reload();
		}
	});
});