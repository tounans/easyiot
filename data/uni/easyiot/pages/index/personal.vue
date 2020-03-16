<template  name="personal">
	<view>
		<view class="cu-bar justify-center bg-white solid-bottom" style="height: 400upx;">
			
			<view class="padding-sm margin-xs radius">
				
				<view class="cu-avatar round xl bg-blue">
					<text class="cuIcon-people"></text>
				</view>
			</view>
			
			<view class="padding-sm margin-xs radius">
				<text class="text-center text-Abc">{{user.username}}</text>
			</view>
			
		</view>
		
		<view class="cu-bar bg-white solid-bottom margin-top">
			<view class="action">
				<text class="cuIcon-title text-orange"></text> 菜单
			</view>
		</view>
		<view class="cu-list menu" >
			
			
			<view class="cu-item">
				<view class="content">
					<text class="cuIcon-pullright text-grey"></text>
					<text class="text-grey">退出</text>
				</view>
			</view>
			
		</view>
		
		<view class="cu-bar tabbar bg-white shadow foot">
			<view class="action" @click="toPage('/pages/index/index')" >
				<view class='cuIcon-cu-image'>
					<image src="/static/tabbar/basics.png"></image>
				</view>
				<view class="text-gray">菜单</view>
			</view>
			
			<view class="action">
				<view class='cuIcon-cu-image'>
					<image src="/static/tabbar/plugin_cur.png"></image>
				</view>
				<view class="text-gray">个人中心</view>
			</view>
		</view>
		
		
		
		
		
		
	</view>
</template>

<script>
	export default {
		data() {
		return {
				user:{
					username:'Easyiot User'
				}
			}
		},
		methods: {
			
			toPage(e){
				
				if(e =="/pages/index/index"){
					uni.redirectTo({
					    url: e
					});
				}else{					
					uni.navigateTo({
						url: e
					});
				}
				
			}
		}
	}
</script>

<style>
</style>
