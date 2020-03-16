import common from '../common.js';
/**
 * 获取用户下所有串口（分页）
 */
export const getDeviceUartList=(page,userDeviceId)=>(
	post("/list",{"page":page,"pageSize":common.pageSize,"userDeviceId":userDeviceId})
)

/**
 * 保存或者添加一个设备
 */
export const editAndSaveDeviceUart=(editAndSaveDeviceUart)=>(
	post("/editAndSaveDeviceUart",editAndSaveDeviceUart)
)

/**
 * 获得所有UART
 */
export const getAll=()=>(
	post("/getAll")
)

function post(url,params){
	return common.postEaysiot("/device/deviceUart"+url,params)
}


export default{
	getAll,
	getDeviceUartList,
	editAndSaveDeviceUart
}