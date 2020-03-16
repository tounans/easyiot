<template name="basics">
	<view>
		<scroll-view scroll-y class="page">
			<image src="/static/BasicsBg.png"
			 mode="widthFix" class="response"></image>
			<view class="nav-list">
				<navigator hover-class="none"  @click="toPage(item.url)" class="nav-li" navigateTo :class="'bg-'+item.color"
				 :style="[{animation: 'show ' + ((index+1)*0.2+1) + 's 1'}]" v-for="(item,index) in elements" :key="index">
					<view class="nav-title">{{item.title}}</view>
					<view class="nav-name">{{item.name}}</view>
					<text :class="'cuIcon-' + item.cuIcon"></text>
				</navigator>
			</view>
			<view class="cu-tabbar-height"></view>
		</scroll-view>
		
		<view class="cu-bar tabbar bg-white shadow foot">
			<view class="action">
				<view class='cuIcon-cu-image'>
					<image src="/static/tabbar/basics_cur.png"></image>
				</view>
				<view class="text-green">菜单</view>
			</view>
			
			<view class="action" >
				<view class='cuIcon-cu-image'  @click="toPage('/pages/index/personal')"  >
					<image src="/static/tabbar/plugin.png"></image>
				</view>
				<view class="text-gray">个人中心</view>
			</view>
			
			
		</view>
	</view>
</template>

<script>
	export default {
		name: "basics",
		data() {
			return {
				elements: [{
						title: '设备',
						name: 'Device',
						color: 'cyan',
						cuIcon: 'newsfill',
						url:'/pages/device/device/list'
					},
					{
						title: '添加设备',
						name: 'Add Device',
						color: 'blue',
						cuIcon: 'colorlens',
						url:'/pages/device/device/add'
					},
					{
						title: '管脚',
						name: 'GPIO',
						color: 'purple',
						cuIcon: 'font',
						url:'/pages/device/gpio/list'
					},
					{
						title: '添加管脚',
						name: 'Add GPIO',
						color: 'mauve',
						cuIcon: 'cuIcon',
						url:'/pages/device/gpio/add'
					},
					{
						title: '串口',
						name: 'UART',
						color: 'pink',
						cuIcon: 'btn',
						url:'/pages/device/uart/list'
					},
					{
						title: '添加串口',
						name: 'Add Uart',
						color: 'brown',
						cuIcon: 'tagfill',
						url:'/pages/device/uart/add'
					},
					{
						title: '管脚日志',
						name: 'GPIO Log',
						color: 'red',
						cuIcon: 'calendar',
						url:'/pages/device/log/gpio'
					},
					{
						title: '串口日志',
						name: 'UART Log',
						color: 'orange',
						cuIcon: 'calendar',
						url:'/pages/device/log/uart'
					},
					{
						title: '实时信息',
						name: 'Real Info',
						color: 'olive',
						cuIcon: 'message',
						url:'/pages/index/wss'
					}
				],
			};
		},
		methods:{
			
			toPage(e){
				
				if(e =="/pages/index/personal"){
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
	.page {
		height: 100vh;
	}
</style>
