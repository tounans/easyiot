<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">添加UART</block>
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
					<view class="title">UART</view>
					<input placeholder="UART" v-model="uart.uartId" name="uartId"></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">波特率</view>
					<input placeholder="波特率" v-model="uart.baud" name="baud"  ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">数据位</view>
					<input placeholder="数据位" v-model="uart.databits" name="databits"  ></input>
				</view>
				
				<view class="cu-form-group">					
					<view class="title">模式</view>		
					<picker @change="methodChange" :value="uart.method" :range="method">
						<view class="picker">
							{{method[uart.method]}}
						</view>
					</picker>					
				</view>
				
				<view class="cu-form-group">
					<view class="title">校验位</view>		
					<picker @change="parityChange" :value="uart.parity" :range="parity">
						<view class="picker">
							{{parity[uart.parity]}}
						</view>
					</picker>					
				</view>				
				
				<view class="cu-form-group">
					<view class="title">模式</view>		
					<picker @change="stopbitsChange" :value="uart.stopbits" :range="stopbits" >
						<view class="picker">
							{{stopbits[uart.stopbits]}}
						</view>
					</picker>					
				</view>
				
				<view class="cu-form-group">					
					<view class="title">状态</view>		
					<picker @change="stateChange" :value="uart.state" :range="state">
						<view class="picker">
							{{state[uart.state]}}
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
	import deviceUart from '../../../js/device/uart.js'
        export default {
            data() {
                return {
					method: ['输入', '输出'],
					parity: ['PAR_EVEN', 'PAR_ODD','PAR_NONE'],
					stopbits: ['STOP_1', 'STOP_2'],
					state: ['禁用', '启用'],
					uart:{
						imei:"",
						method:0,
						uartId:0,
						parity:0,
						stopbits:0,
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
					this.uart.deviceId = this.deviceTemp[e.detail.value].userDeviceId;
				},
				methodChange(e) {
					this.uart.method = e.detail.value
				},
				parityChange(e) {
					this.uart.parity = e.detail.value
				},
				stopbitsChange(e) {
					this.uart.stopbits = e.detail.value
				},
				stateChange(e) {
					this.uart.state = e.detail.value
				},
				sub(){
					var that = this;
					deviceUart.editAndSaveDeviceUart(this.uart).then((res)=>{
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
						
						that.uart.deviceId = that.deviceTemp[0].userDeviceId;
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