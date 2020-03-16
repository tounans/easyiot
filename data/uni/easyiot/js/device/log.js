import common from '../common.js';
/**
 * 获取GPIO日志（分页）
 */
export const getDeviceGpioLogList=(page,userGpioId)=>(
	post("/deviceGpioLogList",{"page":page,"pageSize":common.pageSize,"userGpioId":userGpioId})
)

/**
 * 获取uart日志（分页）
 */
export const getDeviceUartLogList=(page,userUartId)=>(
	post("/deviceUartLogList",{"page":page,"pageSize":common.pageSize,"userUartId":userUartId})
)



function post(url,params){
	return common.postEaysiot("/device/deviceLog"+url,params)
}


export default{
	getDeviceGpioLogList,
	getDeviceUartLogList
}