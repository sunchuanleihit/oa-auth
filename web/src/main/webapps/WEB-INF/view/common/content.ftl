<#macro page title>
<div id="main1" class="main_wrapper" style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main2" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main3" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main4" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main5" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main6" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main7" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main8" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main9" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
<div id="main10" class="main_wrapper"  style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>


<script>

gPageIndex = 1;

function popPageStack() {
	gPageIndex -= 1;
	if (gPageIndex < 1) {
		gPageIndex = 1;
	}
	$('.main_wrapper').hide();
	$("#main" + gPageIndex).show();
}


function pushPageStack(url) {
    if (gPageIndex > 9) {
    	alert("page is too deep");
    }
	$.ajax({
	   type: "get",
	   url: url,
	   async : false,
	   success: function(data, textStatus){
	   	  gPageIndex += 1;
	   	  $(".main_wrapper").hide();
	      $('#main' + gPageIndex).html(data);
	      $('#main' + gPageIndex).show();
	   }
	});
}


function loadCurrentPage(url) {
	$.ajax({
	   type: "get",
	   url: url,
	   async : false,
	   success: function(data, textStatus){
	   	  $(".main_wrapper").hide();
	      $('#main' + gPageIndex).html(data);
	      $('#main' + gPageIndex).show();
	      
	   }
	});
}



function loadFirstPage(url) {
	$.ajax({
	   type: "get",
	   url: url,
	   async : false,
	   success: function(data, textStatus){
	   	  gPageIndex = 1;
	   	  $(".main_wrapper").hide();
	      $('#main' + gPageIndex).html(data);
	      $('#main' + gPageIndex).show();
	      
	   }
	});
}





</script>




</#macro>