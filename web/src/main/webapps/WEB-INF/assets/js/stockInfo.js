var checkStockType = 0;
var specId;
$(".moreInStock").click(function(){
	checkStockType = 2;
	specId = this.dataset.specId;
	$(".modal-title").text("盘盈入库");
});
$(".lessInStock").click(function(){
	checkStockType = 1;
	specId = this.dataset.specId;
	$(".modal-title").text("盘亏入库");
});

$(".submitCheck").click(function(){
	var num = $("#goodAmount").val();
	var reason = $("#returnReason").val();
	if(isNaN(num)){
		alert("请输入正确的库存");
		return false;
	}
	if(reason === "")
	{
		alert("请输入原因");
		return false;
	}
	sspHttpPost("check",{specId:specId,num:num,remark:reason,type:checkStockType},function(){
	});
});


$(".searchBtn").click(function(){
	var specId = $(".specId").val();
	var goodsName = $(".goodsName").val();
	var goodsType = $(".goodsType").val();
	var keywords;
	if(specId !== "")
	{
		keywords = specId;
	}else{
		keywords = goodsName;
	}
	window.location.href = "info?cateId="+goodsType+"&keyWords="+keywords;
});
