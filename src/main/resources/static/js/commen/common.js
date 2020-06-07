/**
 * @type {string}
 * 页面加载获取token//验证是否存在
 */
var Sessiontoken = sessionStorage.getItem('token');
var Cookietoken = getCookie('token');
var token;
// var BASE_URL="http://192.168.1.124:8081";
window.onpageshow = function(){
	if(Sessiontoken == ""||Sessiontoken == null){
		if (Cookietoken == ""||Cookietoken == null){
			window.location = 'login.html';
			return;
		}else{
			token = Cookietoken
		}
	}else{
		token = Sessiontoken;
	}
};
/**
 *
 * @param urlstr    请求地址
 * @param jsonData 参数
 * @param type   请求类型
 * @param datatype 返回类型
 * @param succFun 成功回调
 * @param errFun 失败回调
 * @returns {*|{getAllResponseHeaders, abort, setRequestHeader, readyState, getResponseHeader, overrideMimeType, statusCode}}
 */
//ajax请求
function Ajax(urlStr, jsonData,type,datatype, succFun, errFun) {//异步
	var ajaxRequestObj = $.ajax({
		headers: {
			"Authorization":token//此处放置请求到的用户token
		},
		type: type,
		dataType: datatype,
		url: urlStr,
		traditional: true,
		timeout: 30000,
		data: jsonData,
		success: succFun,
		error: errFun,
		complete: function (XMLHttpRequest, status) {
			if (status == 'timeout') {
				ajaxRequestObj.abort(); //终止操作
				alert("请求超时!");
			}
		}
	});
}

/**
 * 取cookie
 * @param name
 * @returns {string|null}
 */
function getCookie(name) {
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else{
		return null;
	}
}
