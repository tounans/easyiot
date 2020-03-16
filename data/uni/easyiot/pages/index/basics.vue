<template>
	<view>
		<basics v-if="PageCur=='basics'"></basics>
		<personal v-if="PageCur=='personal'"></personal>
		
		<view class="cu-bar tabbar bg-white shadow foot">
			<view class="action" @click="NavChange" data-cur="basics">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/basics' + [PageCur=='basics'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='basics'?'text-green':'text-gray'">菜单</view>
			</view>
			
			<view class="action" @click="NavChange" data-cur="personal">
				<view class='cuIcon-cu-image'>
					<image :src="'/static/tabbar/plugin' + [PageCur == 'personal'?'_cur':''] + '.png'"></image>
				</view>
				<view :class="PageCur=='plugin'?'text-green':'text-gray'">个人中心</view>
			</view>
		</view>
	</view>
	
	
</template>

<script>
	export default {
		data() {
		return {
				PageCur: 'basics'
			}
		},
		methods: {
			NavChange: function(e) {
				this.PageCur = e.currentTarget.dataset.cur
			}
		},onShow() {
			console.log("success")
		}
	}
</script>

<style>

</style>
