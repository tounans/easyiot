<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">UART列表</block>
		</cu-custom>
		
		
	<view class="cu-card case no-card">
		<view class="cu-item shadow">
			
			<view class="cu-list menu-avatar">
				
				
				<view class="cu-item margin-top-sm" v-for="(item,index) in uartList" :key="index" >					
					<view class="flex-sub padding-sm margin-xs radius">						
						<view class="text-grey flex justify-between" @click="toEdit(item.userUartId)" >
							{{ item.alias}}
							<view class="text-gray text-sm">
								<view class='cu-tag round margin-lr-xs bg-blue light'>UART_{{ item.uartId}}</view>
								<view class='cu-tag round margin-lr-xs bg-blue light'>{{ item.method==1?'输入':'输出' }}</view>
								
							</view>
						</view>
						
						<view class="text-gray margin-top-xs text-sm flex justify-between">
							IMEI:{{ item.imei}}
							<view class="text-gray text-sm">
								<button class='cu-btn sm round margin-lr-xs bg-brown light' @click="toLog(item.uartId)" >日志</button>
								<view class='cu-tag round margin-lr-xs bg-blue light'>波特率{{ item.baud }}</view>
								<view class='cu-tag round margin-lr-xs light' :class="item.state?'bg-green':'bg-black'">{{ item.state?'启用':'禁用'}}</view>
							</view>
						</view>
					</view>					
				</view>
				
				
	
			</view>
			
		</view>
	</view>
		
		
	</view>
</template>

<script>
	import deviceUart from'../../../js/device/uart.js'
	export default {
		data() {
			return {				
				CustomBar: this.CustomBar,
				resPage:{},
				uartList:[],
				uartListBack:[]
			};
		},
		methods:{
			toEdit(e){
				// 去编辑页面
				var that = this;				
				uni.navigateTo({
				    url: '/pages/device/uart/edit',
					success() {						
						setTimeout(()=>{							
							uni.$emit('uartEdit', {
								"uart": that.getUserUartId(e)
							})							
							uni.$once('uartUpdata',function(data){
								that.upUart(data.uart);
							})
							
						},800)
					}
				})
				
			},
			toLog(e){
				// 去日志页面
				var that = this;				
				uni.navigateTo({
				    url: '/pages/device/log/uart?uartId='+e,
				})
			},
			getUserUartId(userUartId){
				// 根据userUartId拿出来uart
				for(let index in this.uartList) { 
					if (userUartId == this.uartList[index].userUartId){	
						return this.uartList[index]
					}
				};  
			},
			upUart(e){
				// 修改子页面后返回数据修改本页面数组
				for(let index in this.uartList) {
					if (e.userUartId == this.uartList[index].userUartId){						
						this.uartList[index] = e;
						break;
					}
				};				
			},
			
		},onLoad() {
			var that = this;
			uni.showLoading();
			deviceUart.getDeviceUartList(1).then((res)=>{
				if(res[1].data.meta.success){					
					that.resPage = res[1].data.data;
					that.uartList=that.resPage.records;
				}
			});
			uni.hideLoading();
		},onReachBottom(){
			// 页面触底 追加数据
			var that = this;			
			if(that.resPage.pages >= that.resPage.current+1 ){
				deviceUart.getDeviceUartList(that.resPage.current+1)
				.then(res=>{					
					that.resPage = res[1].data.data;
					that.uartList=that.uartList.concat(res[1].data.data.records)
				});
			}
			
				
		}
	}
</script>

<style>
/* 	page {
		padding-top: 60rpx;
	} */
</style>
