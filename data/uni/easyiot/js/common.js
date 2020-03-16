import Vue from 'vue'
var pageSize = 20;
const baseUrl = "http://localhost/";

function setUser(user){
	uni.setStorage({
	    key: 'easyiot_user',
	    data: user,
	    success: function () {
	        console.info("储存成功");
	    }
	});
}

function getUser(){
	return uni.getStorageSync('easyiot_user');
}

function getToken(){
	return uni.getStorageSync('easyiot_user').token;
}

function getJwt(){
	return uni.getStorageSync('easyiot_user').jwt;
}

function postEaysiot(url,params){
	return uni.request({
		url:baseUrl+url,
		data:params,
		timeout:5000,
		method:'POST',
		header:{
			"content-type":"application/json; charset=utf-8",
			"Authorization":"Bearer "+getJwt()
		}
	})
}

export default {
	postEaysiot,
	setUser,
	getUser,
	pageSize,
	getToken,
	getJwt
}