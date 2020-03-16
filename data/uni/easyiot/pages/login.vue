<template>
	<view>
		<view class="bg-img bg-mask justify-center flex align-center" style="background-image: url('/static/login.png');position: absolute; height: 100%; width: 100%;" >
			<view class="padding-xl text-white">
				
					<view class="padding-xs text-lg">
						<view class="text-center ">EasyIot Login.</view>
					</view>
					
					<view class="cu-form-group margin-top-xl round">
						<input v-model="userInfo.username" style="text-align: center;" placeholder="用户名" name="input"></input>
					</view>
					
					<view class="cu-form-group margin-top-xl round">
						<input v-model="userInfo.password" style="text-align: center;" type="password" placeholder="密码" name="input"></input>
					</view>
					
					
					<view class="padding flex flex-direction margin-top-xl">
						<button @click="login" class="cu-btn bg-grey lg">登录</button>
					</view>
					
					
			</view>
		</view>
	</view>
</template>

<script>
	import Vue from 'vue'
	import user from '../js/user.js'
	import common from '../js/common.js'
	export default {
		data() {
		return {
				CustomBar:this.CustomBar,
				userInfo:{}
			}
		},
		methods: {
			login(){
				user.login(this.userInfo).then((res)=>{
					if(res[1].data.meta.success){
						var token = res[1].data.data;
						console.info(res[1])
						user.userjwt(token).then((res2)=>{
							console.info(res2[1])
							if(res2[1].data.meta.success){
								var jwt = res2[1].data.data;
								this.userInfo.token=token;
								this.userInfo.jwt=jwt;
								common.setUser(this.userInfo);
								
								uni.redirectTo({
								    url: '/pages/index/index'
								});
								
							}
						});
						
					}
				});
			}
		}
	}
	
</script>

<style>
</style>
