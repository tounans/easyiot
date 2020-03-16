import common from '../common.js';

/**
 * 获取用户下所有设备（分页）
 */
export const getDeviceList=(page,imei)=>(
	post("/list",{"page":page,"pageSize":common.pageSize,"imei":imei})
)

/**
 * 根据IMEI查找设备
 */
export const getDeviceByIdImei=(imei)=>(
	post("/getDeviceByIdImei",{"imei":imei})
)

/**
 * 保存或者添加一个设备
 */
export const editAndSaveDevice=(editAndSaveDevice)=>(
	post("/editAndSaveDevice",editAndSaveDevice)
)

/**
 * 验证设备是否被绑定
 */
export const existence=(imei)=>(
	post("/existence?imei="+imei,imei)
)

/**
 * 获得所有设备
 */
export const getAll=()=>(
	post("/getAll")
)

function post(url,params){
	return common.postEaysiot("/device/device"+url,params)
}


export default{
	getDeviceList,
	editAndSaveDevice,
	getDeviceByIdImei,
	existence,
	getAll
}