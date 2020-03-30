<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">GPIO列表</block>
		</cu-custom>
		
		
	<view class="cu-card case no-card">
		<view class="cu-item shadow">
			
			<view class="cu-list menu-avatar">
				
				<view class="cu-item margin-top-sm" v-for="(item,index) in gpioList" :key="index" >					
					<view class="flex-sub padding-sm margin-xs radius">						
						<view class="text-grey flex justify-between"  @click="toEdit(item.userGpioId)">
							{{ item.alias}}
							<view class="text-gray text-sm">
								<view class='cu-tag round margin-lr-xs bg-blue light'>IO_{{ item.gpioId}}</view>
								<view class='cu-tag round margin-lr-xs bg-blue light'>{{ item.method==1?'输出':'中断'}}</view>
								<view class='cu-tag round margin-lr-xs bg-blue light'>默认{{ item.def==1?'高电平':'低电平'}}</view>
							</view>
						</view>
						
						<view class="text-gray margin-top-xs text-sm flex justify-between">
							IMEI:{{ item.imei}}
							<view class="text-gray text-sm">
								<button class='cu-btn sm round margin-lr-xs bg-brown light' @click="toLog(item.gpioId)" >日志</button>
								<view class='cu-tag round margin-lr-xs light' :class="item.current==1?'bg-green':'bg-red'">当前{{ item.current==1?'高电平':'低电平'}}</view>
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
	import deviceGpio from'../../../js/device/gpio.js'
	export default {
		data() {
			return {				
				CustomBar: this.CustomBar,
				resPage:{},
				gpioList:[],
				gpioListBack:[]
			};
		},
		methods:{
			toEdit(e){
				// 去编辑页面
				var that = this;				
				uni.navigateTo({
				    url: '/pages/device/gpio/edit',
					success() {						
						setTimeout(()=>{							
							uni.$emit('gpioEdit', {
								"gpio": that.getUserGpioId(e)
							})							
							uni.$once('gpioUpdata',function(data){
								that.upGpio(data.gpio);
							})
							
						},800)
					}
				})
			},
			toLog(e){
				// 去日志页面
				var that = this;				
				uni.navigateTo({
				    url: '/pages/device/log/gpio?gpioId='+e,
				})
			},
			getUserGpioId(userGpioId){
				// 根据userGpioId拿出来Gpio
				for(let index in this.gpioList) { 
					if (userGpioId == this.gpioList[index].userGpioId){	
						return this.gpioList[index]
					}
				};  
			},
			upGpio(e){
				// 修改子页面后返回数据修改本页面数组
				for(let index in this.gpioList) {
					if (e.userGpioId == this.gpioList[index].userGpioId){						
						this.gpioList[index] = e;
						break;
					}
				};				
			},
			
		},onLoad() {
			var that = this;
			uni.showLoading();
			deviceGpio.getDeviceGpioList(1).then((res)=>{
				if(res[1].data.meta.success){					
					that.resPage = res[1].data.data;
					that.gpioList=that.resPage.records;
				}
			});
			uni.hideLoading();
		},onReachBottom(){
			// 页面触底 追加数据
			var that = this;			
			if(that.resPage.pages >= that.resPage.current+1 ){
				deviceGpio.getDeviceGpioList(that.resPage.current+1)
				.then(res=>{					
					that.resPage = res[1].data.data;
					that.gpioList=that.gpioList.concat(res[1].data.data.records)
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
