<template>
        
	<view>
		
			<cu-custom bgColor="bg-gradual-blue" :isBack="true">
				<block slot="backText">返回</block>
				<block slot="content">编辑GPIO</block>
			</cu-custom>
			
			<form>
				
				<view class="cu-form-group margin-top">
					<view class="title">IMEI</view>
					<input placeholder="IMEI"  :value="gpio.imei" name="imei" disabled ></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">别名</view>
					<input placeholder="别名" :value="gpio.alias" name="alias" disabled></input>
				</view>
				
				<view class="cu-form-group">
					<view class="title">GPIO</view>
					<input placeholder="GPIO" :value="gpio.gpioId" name="gpioId" disabled></input>
				</view>
				
				<view class="cu-form-group margin-top">
					
					<view class="title">当前电平</view>		
					<input placeholder="当前电平" :value="gpio.current==1?'高电平':'低电平'" name="current" disabled></input>
					
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
					<picker @change="stateChange" :value="gpio.stateTmep" :range="stateTmep">
						<view class="picker">
							{{stateTmep[gpio.stateTmep]}}
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
	import deviceGpio from '../../../js/device/gpio.js'
        export default {
            data() {
                return {
					method: ['中断', '输出'],
					def: ['低电平', '高电平'],
					stateTmep: ['禁用', '启用'],
					gpio:{
						imei:"",
						gpioId:0,
						method:0,
						def:0,
						current:0,						
						state:false,
						stateTmep:0
					}
                }
            },
            methods: {
				methodChange(e) {
					this.gpio.method = e.detail.value
				},
				defChange(e) {
					this.gpio.def = e.detail.value
				},
				stateChange(e) {
					this.gpio.stateTmep= e.detail.value;
					this.gpio.state = e.detail.value == 0 ?false:true;
				},
				sub(){
					var that = this;
					deviceGpio.editAndSaveDeviceGpio(this.gpio).then((res)=>{
						if(res[1].data.data == true){
							
							uni.showToast({
							    title: '更新成功',
							    duration: 1500
							});
							
							uni.$emit('gpioUpdata', {
								"gpio": that.gpio
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
				
                uni.$once('gpioEdit',function(data){	
					that.gpio = data.gpio
					if(that.gpio.state){
						that.gpio.state = true;
						that.gpio.stateTmep = 1;
					}else{
						that.gpio.state = false;
						that.gpio.stateTmep = 0;
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