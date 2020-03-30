const baseUrl = "http://127.0.0.1:8040/auth";

function login(user){
	return post("/userlogin",user)
}

function registered(user){
	return post("/registered",user)
}

function userjwt(token){
	
	return uni.request({
		url:baseUrl+"/userjwt",
		timeout:5000,
		method:'POST',
		header:{
			"content-type":"application/x-www-form-urlencoded",
			"uid":token
		}
	})
	
}

function post(url,params){
	return postEaysiot(url,params,"Authorization")
}

function postEaysiot(url,params,Authorization){
	return uni.request({
		url:baseUrl+url,
		data:params,
		timeout:5000,
		method:'POST',
		header:{
			"content-type":"application/x-www-form-urlencoded"
		}
	})
}


export default{
	login,
	userjwt,
	registered
}