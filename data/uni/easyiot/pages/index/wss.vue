<template>
	<view>
		
		<cu-custom bgColor="bg-gradual-blue" :isBack="true">
			<block  slot="backText">返回</block>
			<block  slot="content">实时信息</block>
		</cu-custom>
		
		<view class="cu-chat">
			
			<view  v-for="(item,index) in message" :key="index">
				
				<!-- 自己 -->
				<view class="cu-item self" v-if="item.type=='out'" >				
					<view class="main">
						<view class="content bg-green shadow">
							<text>{{msgOut(item.data)}}</text>
						</view>
					</view>
					
					<view class="cu-avatar round bg-blue light">
						<text class="cuIcon-people"></text>
					</view>
										
					<view class="date">
						<text>20:00</text>
					</view>
					
				</view>
				
				<!-- 对方 -->
				<view class="cu-item" v-if="item.type=='in'" >
					<view class="cu-avatar round bg-cyan light">
						<text class="cuIcon-service"></text>
					</view>
					
					<view class="main">
						<view class="content shadow">
							<text>{{msgOut(item.data)}}</text>
						</view>
					</view>
					
					<view class="date">
						<text>20:00</text>
					</view>
					
				</view>
			
			</view>		
			
			
			
		</view>

		<view class="cu-bar foot input" >
			<button @tap="showModal" data-target="gpio" class="cu-btn margin-lr-xs bg-green shadow">控制GPIO</button>
			<button @tap="showModal" data-target="uart" class="cu-btn margin-lr-xs bg-green shadow">控制UART</button>
			<button @click="clean" class="cu-btn margin-lr-xs bg-green shadow">清屏</button>
		</view>
		
		
		
		<!-- 控制GPIO START -->
		<view class="cu-modal" :class="modalName=='gpio'?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">控制GPIO</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				
				<view class="padding-xl" >					
					<view class="cu-form-group">
						<view class="title">设备</view>
						<picker @change="deviceListChange" :value="deviceIndex" :range="deviceList">
							<view class="picker">
								{{deviceList[deviceIndex]}}
							</view>
						</picker>
					</view>
					<view class="cu-form-group">
						<view class="title">GPIO</view>
						<picker @change="gpioChange" :value="gpioIndex" :range="gpioList">
							<view class="picker">
								{{gpioList[gpioIndex]}}
							</view>
						</picker>						
					</view>
					<view class="cu-form-group">						
						<view class="title">输出电平</view>
						<picker @change="gpioStateChange" :value="gpio.state" :range="gpioState">
							<view class="picker">
								{{gpioState[gpio.state]}}
							</view>
						</picker>						
					</view>
					
				</view>
				
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-green text-green" @tap="hideModal">取消</button>
						<button class="cu-btn bg-green margin-left" @tap="subGpio">确定</button>
					</view>
				</view>
			</view>
		</view>
		<!-- 控制GPIO END -->
		
		
		<!-- 控制UART START -->
		<view class="cu-modal" :class="modalName=='uart'?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">控制UART</view>
					<view class="action" @tap="hideModal">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				
				<view class="padding-xl" >					
					<view class="cu-form-group">
						<view class="title">设备</view>
						<picker @change="deviceListChange" :value="deviceIndex" :range="deviceList">
							<view class="picker">
								{{deviceList[deviceIndex]}}
							</view>
						</picker>
					</view>
					<view class="cu-form-group">
						<view class="title">UART</view>
						<picker @change="uartChange" :value="uartIndex" :range="uartList">
							<view class="picker">
								{{uartList[uartIndex]}}
							</view>
						</picker>						
					</view>
					<view class="cu-form-group">						
						<view class="title">输出信息</view>
						<input placeholder="信息" v-model="uart.msg" name="msg"></input>			
					</view>
					
				</view>
				
				<view class="cu-bar bg-white justify-end">
					<view class="action">
						<button class="cu-btn line-green text-green" @tap="hideModal">取消</button>
						<button class="cu-btn bg-green margin-left" @tap="subUart">确定</button>
					</view>
				</view>
			</view>
		</view>
		<!-- 控制UART END -->
		

	</view>
</template>


<script>
	import Vue from 'vue'
	import device 	  from '../../js/device/device.js'
	import deviceGpio from '../../js/device/gpio.js'
	import deviceUart from '../../js/device/uart.js'
	import common	  from '../../js/common.js'
	export default {
		data() {
			return {
			
				deviceList: [],
				deviceTemp:[],
				
				uartIndex:0,
				uartList: [],
				uartListTemp:[],
				
				gpioIndex:0,
				gpioList: [],				
				gpioListTemp:[],
				
				gpioState: ['低电平', '高电平'],
				deviceIndex:0,
				gpio:{
					gpio:0,
					state:0
				},
				uart:{
					uart:0,
					msg:"",
				},
				baseUrl :"wss://127.0.0.1:8088/ws",
				socketTask:this.socket,
				message:this.message,
				modalName:""
				
			}
		},
		methods: {
			msgOut(str){
				
				
				
				// console.info("msgOut"+str);
				
				let inMsg= JSON.parse(str);
				
			
				// console.info("inMsg"+inMsg)
				
				var resMsg = "";
				resMsg = resMsg + "设备："+inMsg.alias;
				if(inMsg.method.trim() =="gpioOutput"){
					resMsg = resMsg + "\t\n 方法:管脚输出"+"\t\n GPIO："+inMsg.gpio+"\t\n 状态："+(inMsg.state==0?'低电平':'高电平');
				}else if (inMsg.method.trim() =="uartOutput"){
					resMsg = resMsg + "\t\n 方法:串口输出"+"\t\n UART："+inMsg.uart+"\t\n 信息："+inMsg.msg;
				}else if (inMsg.method.trim() =="gpioInput"){
					resMsg = resMsg + "\t\n 方法:管脚输入"+"\t\n GPIO："+inMsg.gpio+"\t\n 状态："+(inMsg.state==0?'低电平':'高电平');
				}else if (inMsg.method.trim() =="readUart"){
					resMsg = resMsg + "\t\n 方法:串口输入"+"\t\n UART："+inMsg.uart+"\t\n 信息："+inMsg.msg;
				}
				
				
				
				// console.info("resMsg"+resMsg);
				return resMsg;
			},
			subGpio(){			
				this.gpio.method = "gpioOutput"
				console.info(this.gpio);
				this.modalName = null;
				this.sub(this.gpio);
			},
			subUart(){				
				this.uart.method = "uartOutput"
				console.info(this.uart);
				this.modalName = null;
				this.sub(this.uart);
			},			
			uartChange(e){
				this.uartIndex = e.detail.value;
				this.uart.uart = e.detail.value;
			},
			gpioChange(e){
				this.gpioIndex = e.detail.value;
				this.gpio.gpio = e.detail.value;
			},
			gpioStateChange(e){
				this.gpio.state = e.detail.value;
			},
			deviceListChange(e) {
				this.deviceIndex = e.detail.value;
				
				let alias = this.deviceTemp[e.detail.value].alias;
				let userDeviceId = this.deviceTemp[e.detail.value].userDeviceId;
				let imei = this.deviceTemp[e.detail.value].imei;
				
				this.gpio.deviceId = userDeviceId;
				this.uart.deviceId = userDeviceId;
				
				this.gpio.imei = imei;
				this.uart.imei = imei;
				
				this.gpio.alias = alias;
				this.uart.alias = alias;
				
				this.getGpio(userDeviceId);
				this.getUart(userDeviceId);
				
			},
			hideModal(e) {
				this.modalName = null
			},
			showModal(e) {
				this.modalName = e.currentTarget.dataset.target
			},
			clean(){
				// 清屏
				Vue.prototype.message=[]
			},
			getUart(deviceId){
				// 获得uart 列表
				this.uartList = [];
				for(let index in this.uartListTemp){
					if(this.uartListTemp[index].deviceId == deviceId){						
						this.uartList.push(this.uartListTemp[index].uartId);
					}
				}
				
				this.uart.uart = this.uartList[0];
			},
			getGpio(deviceId){
				// 获得uart 列表
				this.gpioList = [];
				for(let index in this.gpioListTemp){
					if(this.gpioListTemp[index].deviceId == deviceId){						
						this.gpioList.push(this.gpioListTemp[index].gpioId);
					}
				}
				this.gpio.gpio = this.gpioList[0];
			},
			sub(msg){
				var that = this;
				var text = {
					data:JSON.stringify(msg)
				};
				
				let mes = {
					type:'out',
					data : JSON.stringify(msg)
				}
				// console.info(text)
				that.message=that.message.concat(mes);
				Vue.prototype.message=Vue.prototype.message.concat(mes);
				
				if(Vue.prototype.socket){					
					Vue.prototype.socket.send(text);
				}
				
				setTimeout(function(){
					uni.pageScrollTo({
					    scrollTop: 99999,
					    duration: 0
					});
				},100)
				
			}
		},onUnload(){
			// 将滚动 和 加载定时器关闭
			clearInterval(Vue.prototype.wssTime);
		},onShow() {
			uni.pageScrollTo({
			    scrollTop: 99999,
			    duration: 0
			});
		},onLoad() {
			var that = this;
			// TODO: 这里可以 修改成缓存
			
			device.getAll().then((res)=>{
				if(res[1].data.meta.success){
					that.deviceTemp = res[1].data.data					
					for(let index in that.deviceTemp){
						that.deviceList.push(that.deviceTemp[index].alias);
					}					
		
					let userDeviceId = that.deviceTemp[0].userDeviceId;
					let imei = that.deviceTemp[0].imei;
					let alias = that.deviceTemp[0].alias;
					that.gpio.deviceId = userDeviceId;
					that.uart.deviceId = userDeviceId;
					
					that.gpio.imei = imei;
					that.uart.imei = imei;
					
					that.gpio.alias = alias;
					that.uart.alias = alias;
				}
			});
			
			deviceGpio.getAll().then((res)=>{
				if(res[1].data.meta.success){
					that.gpioListTemp = res[1].data.data
					for(let index in that.gpioListTemp){
						that.gpioList.push(that.gpioListTemp[index].gpioId);
					}					
					that.getGpio(that.gpio.deviceId);
				}
			});
			
			deviceUart.getAll().then((res)=>{
				if(res[1].data.meta.success){
					that.uartListTemp = res[1].data.data					
					for(let index in that.uartListTemp){
						that.uartList.push(that.uartListTemp[index].uartId);
					}					
					that.getUart(that.uart.deviceId);
					console.info(res[1].data.data);
				}
			});
			
			// 以上可以优化
			
			
			if(Vue.prototype.wssTime){
				// 如果已经有定时器 关闭
				clearInterval(Vue.prototype.wssTime);
			}
			
			Vue.prototype.wssTime=setInterval(function(){
				if(Vue.prototype.message.length!=that.message.length){		
					// 如果有新内容 加载
					that.message = Vue.prototype.message;
					
					setTimeout(function(){
						uni.pageScrollTo({
						    scrollTop: 99999,
						    duration: 0
						});
					},200)
				}
			},500);
			
			
			if(!Vue.prototype.socket){
				if(Vue.prototype.message == null || Vue.prototype.message == undefined){
					Vue.prototype.message=[];
					that.message = [];
				}
				
				this.socketTask=uni.connectSocket({
					url: this.baseUrl+"?token="+common.getToken(),
					method:'POST',
					header: {
						'content-type': 'application/json'
					},
					success(){					
						console.log('WebSocket已连接！！');
					},
					fail() {
						Vue.prototype.socket = false;
						console.log('WebSocket连接打开失败，请检查！');
					}});
					
				Vue.prototype.socket = that.socketTask;
					
				uni.onSocketClose((res)=>{
					Vue.prototype.socket = false;
					console.log('WebSocket 已关闭！');
				});
				
			}
			
			
			uni.onSocketMessage(function (res) {
				// console.info("res2:"+res)
				let mes = {
					type:'in',
					data : res.data
				}
				Vue.prototype.message = Vue.prototype.message.concat(mes);				
			});
			
		}
	}
</script>
	
<style>
page{
  padding-bottom: 100upx;
}
</style>
