<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block slot="backText">返回</block>
			<block slot="content">设备列表</block>
		</cu-custom>
		
		<view class="cu-bar bg-white search fixed" :style="[{top:CustomBar + 'px'}]">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input v-model="searchImeiValue" type="text" placeholder="输入IMEI" confirm-type="search"></input>
			</view>
			<view class="action">
				<button @click="searchImei" class="cu-btn bg-gradual-blue shadow-blur round">搜索</button>
			</view>
		</view>
	
		
		<view class=" margin-top cu-list menu" :class="[menuBorder?'sm-border':'',menuCard?'card-menu margin-top':'']">
			
			<view class="cu-item"  v-for="(item,index) in deviceList" :key="index" >
				<view class="content padding-tb-sm">
					<view>
						<text class="cuIcon-sort text-blue margin-right-xs"></text> {{ item.alias}}</view>
					<view class="text-gray text-sm">
						<text class="margin-right-xs"></text>IMEI:{{ item.imei}}</view>
				</view>
				<view class="action">
					<text  class="cuIcon-right margin-right-xs" @click="toEdit(item.userDeviceId)" ></text>
				</view>
			</view>
			
		</view>
			
		
		
	</view>
</template>

<script>
	import device from '../../../js/device/device.js'
	export default {
		data() {
			return {				
				CustomBar: this.CustomBar,
				menuCard: false,				
				menuBorder: false,
				resPage:{},
				deviceList:[],
				searchImeiValue:"",
				backDeviceList:[]
			};
		},
		methods:{
			toEdit(e){
				// 去编辑页面
				var that = this;				
				uni.navigateTo({
				    url: '/pages/device/device/edit',
					success() {						
						setTimeout(()=>{							
							uni.$emit('deviceEdit', {
								"device": that.getDeviceUserId(e)
							})							
							uni.$once('deviceUpdata',function(data){
								that.upDevice(data.device);
							})
							
						},800)
					}
				})
				
			},
			getDeviceUserId(userDeviceId){
				// 根据userDeviceId拿出来Device
				for(let index in this.deviceList) { 
					if (userDeviceId == this.deviceList[index].userDeviceId){						
						return this.deviceList[index]
					}
				};  
			},
			upDevice(e){
				// 修改子页面后返回数据修改本页面数组
				for(let index in this.deviceList) {
					if (e.userDeviceId == this.deviceList[index].userDeviceId){						
						this.deviceList[index] = e;
						break;
					}
				};				
			},
			searchImei(){
				// 搜索IMEI
				
				if(!this.searchImeiValue){
					// 如果是空的 加载原数据
					if(this.backDeviceList.size()>0){
						this.deviceList = this.backDeviceList;
						this.backDeviceList = [];
					}
				}else if(this.resPage.pages == this.resPage.current){
					// 如果所有设备都加载 就在列表里面查找
					for(let index in this.deviceList) {
						if (this.searchImeiValue == this.deviceList[index].imei){
							let temp =this.deviceList[index];
							this.backDeviceList = this.deviceList;
							this.deviceList = [];
							this.deviceList.push(temp);
						}
					}; 
				}else{
					var that = this;
					// 通过服务器查找
					device.getDeviceByIdImei(this.searchImeiValue)
					.then(res=>{
						if(res[1].data.data!=null){
							that.deviceList=that.deviceList.concat(res[1].data.data)
							this.backDeviceList = this.deviceList;
							this.deviceList = [];
							this.deviceList.push(res[1].data.data);
						}
					});
				}
				
				
			}
		},onLoad() {
			var that = this;
			uni.showLoading();
			device.getDeviceList(1,"","")
			.then(res=>{
				console.info(res)
				if(res[1].data.meta.success){					
					that.resPage = res[1].data.data;
					that.deviceList=that.resPage.records;
				}
			});
			uni.hideLoading();
		},onReachBottom(){
			// 页面触底 追加数据
			var that = this;			
			if(that.resPage.pages >= that.resPage.current+1 ){
				device.getDeviceList(that.resPage.current+1,"","")
				.then(res=>{					
					that.resPage = res[1].data.data;
					that.deviceList=that.deviceList.concat(res[1].data.data.records)
				});
			}
			
				
		}
	}
</script>

<style>
	page {
		padding-top: 60rpx;
	}
</style>
