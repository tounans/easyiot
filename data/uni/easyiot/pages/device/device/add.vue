<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">添加设备</block>
			</cu-custom>
			
			
			<form>
				
				<view class="cu-form-group margin-top">
					<view class="title">IMEI</view>
					<input @blur="checkImei" placeholder="IMEI" v-model="device.imei" name="imei"></input>
					<text @click="scanCode" class='cuIcon-camera text-blue'></text>	
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
				
							
				<view class="padding">
					<button @click="sub" class="cu-btn block bg-blue margin-tb-sm lg">保存添加</button>
				</view>				
		
				
			</form>
	
	
	</view>
</template>

<script>
	import device from '../../../js/device/device.js'
        export default {
            data() {
                return {
					submit:true,
					existence:false,
					device:{
						state:false,
					}
                }
            },
            methods: {
				SwitchState(e) {
					this.device.state = e.detail.value;
				},
				scanCode(){
					
					let that = this;					
					uni.scanCode({
					    success: function (res) {
							that.device.imei=res.result;
							that.checkImei();
					    }
					});
				},
				checkImei(){
					device.existence(this.device.imei).then((res)=>{
						if(res[1].data.data == true){
							this.existence=true
						}else{
							uni.showToast({
							    title: '此设备已被绑定!',
								icon:'none',
							    duration: 1000
							});
						}
					});
				},
				sub(){
					if(this.existence){
						if(this.submit){
							var that = this;
							device.editAndSaveDevice(this.device).then((res)=>{
								if(res[1].data.data == true){
									that.submit=false;
									uni.showToast({
									    title: '添加成功',
									    duration: 1500
									});
																
								}else{
									
									uni.showToast({
									    title: '添加失败',
										icon:'none',
									    duration: 1500
									});
									
								}
							});	
						}
						
					}else{
						uni.showToast({
						    title: '此设备已被绑定!',
							icon:'none',
						    duration: 1500
						});
					}
										  
				}
				
            }
				
        }
</script>

<style>
</style>