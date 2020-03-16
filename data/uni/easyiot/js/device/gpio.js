import common from '../common.js';
/**
 * 获取用户下所有管脚（分页）
 */
export const getDeviceGpioList=(page,userDeviceId)=>(
	post("/list",{"page":page,"pageSize":common.pageSize,"userDeviceId":userDeviceId})
)

/**
 * 保存或者添加一个设备
 */
export const editAndSaveDeviceGpio=(editAndSaveDeviceGpio)=>(
	post("/editAndSaveDeviceGpio",editAndSaveDeviceGpio)
)

/**
 * 获得所有GPIO
 */
export const getAll=()=>(
	post("/getAll")
)

function post(url,params){
	return common.postEaysiot("/device/deviceGpio"+url,params)
}


export default{
	getAll,
	getDeviceGpioList,
	editAndSaveDeviceGpio
}