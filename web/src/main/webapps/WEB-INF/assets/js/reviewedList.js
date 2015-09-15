//finish packing
var orderList = [];
$(".sendOrder").click(function(){
	orderList = [];
	orderList.push(this.dataset.orderSn);
});

$(".submitSendOrder").click(function(){
	var senderId = $("input[name=senderRadios][type='radio']:checked").val();
	sspHttpPost("finishPacking",{"orderList":orderList,"senderId":senderId},function(data){
		if(data.code===200){
			$('#myModal').modal('hide');
			alert("操作成功");
			window.location.reload();
		}
	});
	orderList=[];
});

$(".sendAll").click(function(){
	orderList=[];
	var checkItems = $(".checkItem:checked");
	if(checkItems.length===0)
	{
		alert("请选择至少一个订单！");
		return false; 
	}
	$.each(checkItems,function(i,e){
		orderList.push(e.dataset.orderSn);
	});
});

//refuse order
var refusedOrderSn="";
$(".refuseOrderItem").click(function(){
	refusedOrderSn = this.dataset.orderSn;
});
$(".refuseOrder").click(function(){
	var refuseId = $("input[name=refuseReasonRadios][type='radio']:checked").val();
	sspHttpPost("refuseOrder",{"taoOrderSn":refusedOrderSn,"refuseId":refuseId},function(data){
		if(data.code===200)
		{
			$('#refuseModal').modal('hide');
			alert("操作成功");
			window.location.reload();
		}
	});
});
