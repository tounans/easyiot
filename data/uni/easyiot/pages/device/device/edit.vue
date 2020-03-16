<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">编辑设备</block>
			</cu-custom>
			
			
			<form>
				
				<view class="cu-form-group margin-top">
					<view class="title">IMEI</view>
					<input placeholder="IMEI" name="imei" :value="device.imei" disabled ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">别名</view>
					<input placeholder="别名" v-model="device.alias" name="alias"></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">备注</view>
					<input placeholder="备注" v-model="device.remark" name="remark"></input>
				</view>	
				
				<view class="cu-form-group">
					<view class="title">状态</view>
					<switch @change="SwitchState" :class="device.state?'checked':''" :checked="device.state?true:false"></switch>
				</view>
				
				
				<view class="cu-form-group margin-top">
					
					<view class="title">信号</view>					
					<view class="cu-progress">
						<view class="bg-blue" :style="[{ width:loading?signal:''}]">{{signal}}</view>
					</view>
					
				</view>
				
				<view class="cu-form-group">
					<view class="title">版本号</view>
					<input placeholder="版本号" :value="device.version" name="version" disabled ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">固件名称</view>
					<input placeholder="固件名称" :value="device.firmwareName" name="firmwareName" disabled ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">基站定位信息</view>
					<input placeholder="基站定位信息" :value="device.lbs" name="lbs" disabled ></input>
				</view>
				
				
				<view class="padding">
					<button @click="sub" class="cu-btn block bg-blue margin-tb-sm lg">保存修改</button>
				</view>				
		
				
			</form>
	
	
	</view>
</template>

<script>
	import device from '../../../js/device/device.js'
        export default {
            data() {
                return {
					signal:0,
					loading:false,
					device:{
						imei:"",
						state:false
					}
                }
            },
            methods: {
				
				SwitchState(e) {
					this.device.state = e.detail.value;
				},
				sub(){
					var that = this;
					device.editAndSaveDevice(this.device).then((res)=>{
						if(res[1].data.data == true){
							
							uni.showToast({
							    title: '更新成功',
							    duration: 1500
							});
							
							uni.$emit('deviceUpdata', {
								"device": that.device
							})
							
						}else{
							
							uni.showToast({
							    title: '更新失败',
								icon:'none',
							    duration: 1500
							});
							
						}
					});						  
				}
				
            },
            onLoad(e) {
				// 加载
				var that = this;		

				
                uni.$once('deviceEdit',function(data){	
					that.device = data.device
					
					that.signal = parseFloat(that.device.networkSignal / 0.31).toFixed(2)+'%'
					setTimeout(function() {
						that.loading = true
					}, 500)
				})
				
            }
				
        }
</script>

<style>
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>