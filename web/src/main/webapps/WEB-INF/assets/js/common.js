$(".checkAll").click(function(){
	var checkItems = $(".checkItem");
	var allChecked = true;
	$.each(checkItems, function(i,e){
		if(!e.checked){
			allChecked = false;
			return false;
		}
	});
	if(allChecked){
		$.each(checkItems, function(i,e){
			e.checked = false;
		});
		this.checked = false;
	}
	else{
		$.each(checkItems, function(i,e){
			e.checked = true;
		});
		this.checked = true;
	}
});

$(".dropdown-menu li").click(function(){
	alert(this.children().text());
});