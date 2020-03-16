<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">添加GPIO</block>
			</cu-custom>
			
			
			<form>
				
				<view class="cu-form-group margin-top">
					<view class="title">设备</view>
					<picker @change="deviceListChange" :value="deviceIndex" :range="deviceList">
						<view class="picker">
							{{deviceList[deviceIndex]}}
						</view>
					</picker>
				</view>
		
				<view class="cu-form-group">
					<view class="title">GPIO</view>
					<input placeholder="GPIO" v-model="gpio.gpioId" name="gpioId"></input>
				</view>

				<view class="cu-form-group margin-top">
					
					<view class="title">默认电平</view>
					<picker @change="defChange" :value="gpio.def" :range="def">
						<view class="picker">
							{{def[gpio.def]}}
						</view>
					</picker>
					
				</view>
				
				<view class="cu-form-group">
					
					<view class="title">模式</view>		
					<picker @change="methodChange" :value="gpio.method" :range="method">
						<view class="picker">
							{{method[gpio.method]}}
						</view>
					</picker>
					
				</view>
				
				<view class="cu-form-group">
					
					<view class="title">状态</view>		
					<picker @change="stateChange" :value="gpio.state" :range="state">
						<view class="picker">
							{{state[gpio.state]}}
						</view>
					</picker>
					
				</view>
								
				<view class="padding">
					<button @click="sub" class="cu-btn block bg-blue margin-tb-sm lg">保存修改</button>
				</view>				
		
				
			</form>
	
	
	</view>
</template>

<script>
	import device 	  from '../../../js/device/device.js'
	import deviceGpio from '../../../js/device/gpio.js'
        export default {
            data() {
                return {
					method: ['中断', '输出'],
					def: ['低电平', '高电平'],
					state: ['禁用', '启用'],
					gpio:{
						imei:"",
						method:0,
						def:0,
						current:0,
						state:0
					},
					deviceTemp:[],
					deviceList:[],
					deviceIndex:0
                }
            },
            methods: {
				deviceListChange(e) {
					this.deviceIndex = e.detail.value;
					this.gpio.deviceId = this.deviceTemp[e.detail.value].userDeviceId;
				},
				methodChange(e) {
					this.gpio.method = e.detail.value;
				},
				defChange(e) {
					this.gpio.def = e.detail.value;
				},
				stateChange(e) {
					this.gpio.state = e.detail.value;
				},
				sub(){
					var that = this;
					deviceGpio.editAndSaveDeviceGpio(this.gpio).then((res)=>{
						if(res[1].data.data == true){
							
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
				
            },
            onLoad(e) {
				// 加载
				var that = this;				
				device.getAll().then((res)=>{
					if(res[1].data.meta.success){
						that.deviceTemp = res[1].data.data
						
						for(let index in that.deviceTemp){
							that.deviceList.push(that.deviceTemp[index].alias);
						}
						
						that.gpio.deviceId = that.deviceTemp[0].userDeviceId;
					}
				});
				
				
                				
            }
				
        }
</script>

<style>
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>