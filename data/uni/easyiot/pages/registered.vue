<template>
	<view>
		<view class="bg-img bg-mask justify-center flex align-center" style="background-image: url('/static/login.png');position: absolute; height: 100%; width: 100%;" >
			<view class="padding-xl text-white">
				
					<view class="padding-xs text-lg">
						<view class="text-center ">EasyIot Registered.</view>
					</view>
					
					<view class="cu-form-group margin-top-xl round">
						<input v-model="userInfo.username" style="text-align: center;" placeholder="用户名" name="input"></input>
					</view>
					
					<view class="cu-form-group margin-top-xl round">
						<input v-model="userInfo.password" style="text-align: center;" type="password" placeholder="密码" name="input"></input>
					</view>
					
					<view class="padding flex flex-direction margin-top-xl">
						<button @click="registered" class="cu-btn bg-orange lg">注册</button>
					</view>
					
					
					<view class="padding flex flex-direction margin-top-xs">
						<button @click="back" class="cu-btn bg-grey lg">返回</button>
					</view>
					
			</view>
		</view>
	</view>
</template>

<script>
	import user from '../js/user.js'
	import common from '../js/common.js'
	export default {
		data() {
		return {
				userInfo:{}
			}
		},
		methods: {
			back(){
				uni.redirectTo({
				    url: '/pages/login'
				});
			},
			registered(){
				user.registered(this.userInfo).then((res)=>{
					if(res[1].data.meta.success){
						uni.showToast({
						    title: '注册成功~',
						    duration: 2000
						});
						
						uni.redirectTo({
						    url:'/pages/login'
						});
						
					}else{
						uni.showToast({
						    title: '用户名可能被占用咯~',
							icon:'none',
						    duration: 2000
						});
					}
				});
			}
		}
	}
	
</script>

<style>
</style>
