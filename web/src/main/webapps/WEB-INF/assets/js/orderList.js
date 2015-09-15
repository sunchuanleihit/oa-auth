function getInfo(orderSn){
$.ajax({    
    url:"getOrderInfo",
    data:{    
         "taoOrderSn" : orderSn    
    },    
    type:'get',    
    cache:false,    
    dataType:'json',    
    success:function(data) {   
    	setDataToModal(data);
    },    
    error : function() {    
          alert("服务器异常！");    
     }    
});  
}
function search(){
	$("#search").submit();
}
function setDataToModal(data){
	var createTime = data.createTime;
	var finishTime = data.finishTime;
	$(".createTime").text(createTime);
	$(".finishTime").text(finishTime);
	$(".consignee").text(data.deliveryInfo.consignee);
	var specList = data.specList;
	$(".tbody").children().remove();
	$.each(specList,function(i,e){
		$(".tbody").append("<tr "+"data-spec-id="+e.specId+">"+"<td>"+e.specName+"</td>"+"<td>"
				+e.buyNum+"</td>"+"<td>"+e.sellPrice+"</td>"+"<td>"+ e.sellPrice +"</td></tr>");
	});
}