<#macro page title>
<div id="main" class="main_wrapper" style="margin-left: 238px; padding: 10px; background: #ffffff;"></div>
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
	var suffix = "";
	if (gPageIndex > 2) {
		gPageIndex -= 1;
		suffix = gPageIndex;
	} else if (gPageIndex == 2) {
		gPageIndex -= 1;
		suffix = "";
	} else {
		gPageIndex = 1;
	}
	$('.main_wrapper').hide();
	$("#main" + suffix).show();
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



function showPage() {
	gPageIndex = 1;
	$(".main_wrapper").hide();
	$('#main').show();
}


function loadPage(url) {
	$.ajax({
	   type: "get",
	   url: url,
	   async : false,
	   success: function(data, textStatus){
	   	  gPageIndex = 1;
	   	  $(".main_wrapper").hide();
	      $('#main').html(data);
	      $('#main').show();
	      
	   }
	});
}


function showPage2() {
	$('.main_wrapper').hide();
	$("#main2").show();
}



function loadPage2(url) {
	$.ajax({
	   type: "get",
	   url: url,
	   async : false,
	   success: function(data, textStatus){
	   		$(".main_wrapper").hide();
	   		$("#main2").html(data);
	   		$("#main2").show();
	   		
	   }
	});
}


</script>




</#macro>