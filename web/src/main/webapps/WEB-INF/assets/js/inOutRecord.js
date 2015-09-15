$(".searchBtn").click(function(){
	var specId = $(".specId").val();
	var goodsName = $(".goodsName").val();
	var inOutType = $(".inOutType").val();
	var startDate = $(".startDate").val();
	var endDate = $(".endDate").val();
	var keywords = "";
	if(specId !== "")
	{
		keywords = specId;
	}else{
		keywords = goodsName;
	}
	if(startDate > endDate)
	{
		alert("请输入正确起止日期");
		return false;
	}
	window.location.href = "history?keyWords="+keywords+"&type="+inOutType+"&beginTime="+startDate+"&endTime="+endDate;
});