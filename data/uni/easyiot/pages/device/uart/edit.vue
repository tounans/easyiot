<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">编辑UART</block>
			</cu-custom>
			
			
			<form>
				
				<view class="cu-form-group margin-top">
					<view class="title">IMEI</view>
					<input placeholder="IMEI"  :value="uart.imei" name="imei" disabled ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">别名</view>
					<input placeholder="别名" :value="uart.alias" name="alias" disabled></input>
				</view>
				
							
			
	
				<view class="cu-form-group margin-top">
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
					<picker @change="stateChange" :value="uart.stateTmep" :range="stateTmep">
						<view class="picker">
							{{stateTmep[uart.stateTmep]}}
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
	import deviceUart from '../../../js/device/uart.js'
        export default {
            data() {
                return {
					method: ['输入', '输出'],
					parity: ['PAR_EVEN', 'PAR_ODD','PAR_NONE'],
					stopbits: ['STOP_1', 'STOP_2'],
					stateTmep: ['禁用', '启用'],
					uart:{
						imei:"",
						method:0,
						uartId:0,
						parity:0,
						stopbits:0,
						state:false,
						stateTmep:0
					}
                }
            },
            methods: {
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
					this.uart.stateTmep= e.detail.value;
					this.uart.state = e.detail.value == 0 ?false:true;
				},
				sub(){
					var that = this;
					deviceUart.editAndSaveDeviceUart(this.uart).then((res)=>{
						if(res[1].data.data == true){
							
							uni.showToast({
							    title: '更新成功',
							    duration: 1500
							});
							
							uni.$emit('uartUpdata', {
								"uart": that.uart
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
				
                uni.$once('uartEdit',function(data){	
					that.uart = data.uart
					if(that.uart.state){
						that.uart.state = true;
						that.uart.stateTmep = 1;
					}else{
						that.uart.state = false;
						that.uart.stateTmep = 0;
					}
				})
				
            }
				
        }
</script>

<style>
	.cu-form-group .title {
		min-width: calc(4em + 15px);
	}
</style>