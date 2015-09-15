/**
 * 输出，调试用
 * @param data
 */
function sspEcho(data) {
	console.log(data);
	alert(data);
}

/**
 * add date format support
 * @param date
 * @param format
 * @author: meizz
 * @from http://stackoverflow.com/questions/1056728/where-can-i-find-documentation-on-formatting-a-date-in-javascript
 */
function dateFormat(date,format) {
	var o = {
		"M+" : date.getMonth() + 1, //month
		"d+" : date.getDate(), //day
		"h+" : date.getHours(), //hour
		"m+" : date.getMinutes(), //minute
		"s+" : date.getSeconds(), //second
		"q+" : Math.floor((date.getMonth() + 3) / 3), //quarter
		"S" : date.getMilliseconds()
	//millisecond
	}

	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (date.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
 * SSP服务的get请求
 * @param url 请求的URL
 * @param data 请求data
 * @param callback 请求成功后的回调函数
 */
function sspHttpGet(url, data, callback) {
	sspAjax(url, "GET", true, false, null, data, callback);
}

/**
 * SSP服务的PUT POST请求,
 * SSP的Post请求均使用json做为参数
 * @param url
 * @param data: map数据data
 * @param callback 请求成功后的回调函数
 */
function sspHttpPost(url, data, callback) {
	sspAjax(url, "POST", true, false, "application/json", JSON.stringify(data),
			callback);
}

function sspHttpPut(url, data, callback) {
	sspAjax(url, "PUT", true, false, "application/json", JSON.stringify(data),
			callback);
}

/**
 * SSP 服务的delete请求
 * @param url
 * @param data map数据data
 * @param callback 请求成功后的回调函数
 */
function sspHttpDelete(url, data, callback) {
	sspAjax(url, "DELETE", true, false, null, data, callback);
}

var STATUS_CODE_PROCESS = {
	// 200 访问成功
	200 : function() {
		// nothing to do
	},

	// Field
	400 : function(response) {
	},

	// 认证失败 
	401 : function() {
	},
	
	// 404 not found
	404 : function() {
		// 暂时不处理
	},

	// global error
	420 : function (response) {
	},
	
	// 500 internal server error
	500 : function() {
		// 暂时不处理 
	}
}

/**
 * SSP的Ajax请求，所有的其它请求都是基于ajax请求来实现
 * @param url
 * @param data
 * @param async
 * @param callback
 */
function sspAjax(url, type, async, cache, contentType, data, callback) {
	$.ajax({
		'url' : url,
		'type' : type,
		'timeout' : 60000, // timeout for 60 sec
		'async' : async,
		'cache' : cache,
		'contentType' : contentType,
		'data' : data,
		statusCode : STATUS_CODE_PROCESS,
		success : function(data, textStatus) {
			if (callback != null) {
				callback(data);
			}
		}
	});
}
