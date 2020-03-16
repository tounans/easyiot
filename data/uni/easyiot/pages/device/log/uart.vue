<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">UART日志</block>
		</cu-custom>
		
		
		<view class="">
			<view class="cu-item shadow">
				
				<view class="cu-chat">
					<view  v-for="(item,index) in logList" :key="index">
						
						<!-- 自己 -->
						<view class="cu-item self" v-if="item.type=='1'" >				
							<view class="main">
								<view class="content bg-green shadow">
									<text>{{item.data}}</text>
								</view>
							</view>
							
							<view class="cu-avatar radius bg-blue light">
								<text>{{item.uartId}}</text>
							</view>
												
							<view class="date" style="bottom:10upx">								
								<text>{{item.alias}} IMEI:{{item.imei}}</text>
								<br>
								<text>{{item.addTime.replace('T',' ')}}</text>
							</view>
						</view>
						
						<!-- 对方 -->
						<view class="cu-item" v-if="item.type=='0'" >
							<view class="cu-avatar radius bg-cyan light">
								<text>{{item.uartId}}</text>
							</view>
							
							<view class="main">
								<view class="content shadow">
									<text>{{item.data}}</text>
								</view>
							</view>
							
							<view class="date" style="bottom:10upx">
								<text>{{item.alias}} IMEI:{{item.imei}}</text>
								<br>
								<text>{{item.addTime.replace('T',' ')}}</text>
							</view>
						</view>
					
					
					
					</view>		
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import log from '../../../js/device/log.js'
	export default {
		data() {
			return {
				uartId:null,
				resPage:{},
				logList:[],
				InputBottom: 0
			};
		},
		methods: {
			InputFocus(e) {
				this.InputBottom = e.detail.height
			},
			InputBlur(e) {
				this.InputBottom = 0
			}
		},onLoad(e) {
			let uartId = e.uartId;
			this.uartId = uartId;
			var that = this;
			uni.showLoading();
			log.getDeviceUartLogList(1,uartId).then((res)=>{
				if(res[1].data.meta.success){					
					that.resPage = res[1].data.data;
					that.logList=that.resPage.records;
				}
			});
			uni.hideLoading();
		},onReachBottom(){
			// 页面触底 追加数据
			var that = this;			
			if(that.resPage.pages >= that.resPage.current+1 ){
				log.getDeviceUartLogList(that.resPage.current+1,that.uartId)
				.then(res=>{					
					that.resPage = res[1].data.data;
					that.logList=that.logList.concat(res[1].data.data.records)
				});
			}
			
				
		}
	}
</script>

<style>
page{
  padding-bottom: 100upx;
}
</style>
