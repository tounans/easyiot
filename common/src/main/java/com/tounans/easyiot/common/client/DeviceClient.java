package com.tounans.easyiot.common.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.common.view.device.DeviceGpioView;
import com.tounans.easyiot.common.view.device.DeviceUartLogView;
import com.tounans.easyiot.common.view.device.DeviceUartView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value = "easyiot-device",fallback = DeviceHystrix.class)
@FeignClient(value = "server-device")
public interface DeviceClient {

    //  ---------------  device start   ---------------
    @RequestMapping(value = "/device/list", method = RequestMethod.POST)
    IPage<Device> deviceList(@RequestParam("userId") Integer userId,
                            @RequestParam(defaultValue = "1", required = false) Integer page,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam("imei") String imei);

    @RequestMapping(value = "/device/getDeviceById", method = RequestMethod.POST)
    Device getDeviceById(@RequestParam("userId") Integer userId,
                         @RequestParam("userDeviceId") Integer userDeviceId);

    @RequestMapping(value = "/device/getDeviceByUserIdAndImei", method = RequestMethod.POST)
    Device getDeviceByUserIdAndImei(@RequestParam("userId") Integer userId,
                                    @RequestParam("imei") String imei);

    @RequestMapping(value = "/device/getDeviceByImei", method = RequestMethod.POST)
    Device getDeviceByImei(@RequestParam("imei") String imei);

    @RequestMapping(value = "/device/editAndSaveDevice", method = RequestMethod.POST)
    boolean editAndSaveDevice(@RequestBody Device device);

    @RequestMapping(value = "/device/existence", method = RequestMethod.POST)
    boolean existenceImei(@RequestParam("imei") String imei);

    @RequestMapping(value = "/device/getAll", method = RequestMethod.POST)
    List<Device> getAllDevice(@RequestParam("userId") Integer userId);
    //  ---------------  device end   ---------------


    //  ---------------  DeviceGpio start   ---------------

    /**
     * 获得deviceGpio 分页的
     *
     * @param userId
     * @param page
     * @param userDeviceId
     * @return
     */
    @RequestMapping(value = "/deviceGpio/deviceGpioList", method = RequestMethod.POST)
    IPage<DeviceGpio> deviceGpioList(@RequestParam("userId") Integer userId,
                                     @RequestParam(defaultValue = "1", required = false) Integer page,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("userDeviceId") Integer userDeviceId);

    /**
     * 根据Devicegpio id 获得
     *
     * @param userId
     * @param userGpioId
     * @return
     */
    @RequestMapping(value = "/deviceGpio/getDeviceGpioById", method = RequestMethod.POST)
    DeviceGpioView getDeviceGpioById(@RequestParam("userId") Integer userId,
                                     @RequestParam("userGpioId") Integer userGpioId);

    /**
     * 更新或者添加GPIO
     *
     * @param userId
     * @param deviceGpio
     * @return
     */
    @RequestMapping(value = "/deviceGpio/editAndSaveDeviceGpio", method = RequestMethod.POST)
    boolean editAndSaveDeviceGpio(@RequestParam("userId") Integer userId,
                                  @RequestBody DeviceGpio deviceGpio);

    /**
     * 根据设备ID 和 gpioID 获得
     *
     * @param userDeviceId
     * @param gpioId
     * @return
     */
    @RequestMapping(value = "/deviceGpio/getByUserDeviceIdAndGpioId", method = RequestMethod.POST)
    DeviceGpio getByUserDeviceIdAndGpioId(@RequestParam("userDeviceId") Integer userDeviceId,
                                          @RequestParam("gpioId") Integer gpioId);

    /**
     * 更新GPIO状态
     *
     * @param userDeviceId
     * @param userId
     * @param gpio
     * @param state
     * @return
     */
    @RequestMapping(value = "/deviceGpio/updateCurrent", method = RequestMethod.POST)
    boolean updateCurrent(@RequestParam("userDeviceId") Integer userDeviceId,
                          @RequestParam("userId") Integer userId,
                          @RequestParam("gpio") Integer gpio,
                          @RequestParam("state") Integer state);

    /**
     * 根据用户ID 获得所有GPIO
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deviceGpio/getAll", method = RequestMethod.POST)
    List<DeviceGpio> getAllDeviceGpio(@RequestParam("userId") Integer userId);



    /**
     * 设备初始化使用
     * @param userId
     * @param userDeviceId
     * @param state
     * @return
     */
    @RequestMapping(value = "/deviceGpio/listByUserIdAndDeviceIdAndState", method = RequestMethod.POST)
    List<DeviceGpio> gpioListByUserIdAndDeviceIdAndState(@RequestParam("userId") Integer userId,
                                                     @RequestParam("userDeviceId") Integer userDeviceId,
                                                     @RequestParam("state") boolean state);
    //  ---------------  DeviceGpio end   ---------------


    //  ---------------  DeviceUart start   ---------------

    /**
     * 获得Device Uart分页
     *
     * @param userId
     * @param page
     * @param pageSize
     * @param userDeviceId
     * @return
     */
    @RequestMapping(value = "/deviceUart/deviceUartList", method = RequestMethod.POST)
    IPage<DeviceUartView> deviceUartList(@RequestParam("userId") Integer userId,
                                        @RequestParam(defaultValue = "1", required = false) Integer page,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("userDeviceId") Integer userDeviceId);

    /**
     * 根据用户的Device UartID 获得
     *
     * @param userId
     * @param userUartId
     * @return
     */
    @RequestMapping(value = "/deviceUart/getDeviceUartById", method = RequestMethod.POST)
    DeviceUartView getDeviceUartById(@RequestParam("userId") Integer userId,
                                     @RequestParam("userUartId") Integer userUartId);


    /**
     * 更新或者添加Uart
     *
     * @param userId
     * @param deviceUart
     * @return
     */
    @RequestMapping(value = "/deviceUart/editAndSaveDeviceUart", method = RequestMethod.POST)
    boolean editAndSaveDeviceUart(@RequestParam("userId") Integer userId,
                                  @RequestBody DeviceUart deviceUart);

    /**
     * 根据设备ID 和 UartID 获得
     *
     * @param userDeviceId
     * @param uartId
     * @return
     */
    @RequestMapping(value = "/deviceUart/getByUserDeviceIdAndUartId", method = RequestMethod.POST)
    DeviceUart getByUserDeviceIdAndUartId(@RequestParam("userDeviceId") Integer userDeviceId,
                                          @RequestParam("uartId") Integer uartId);

    /**
     * 根据用户ID 获得所有Uart
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deviceUart/getAll", method = RequestMethod.POST)
    List<DeviceUart> getAll(@RequestParam("userId") Integer userId);

    /**
     * 设备初始化使用
     * @param userId
     * @param userDeviceId
     * @param state
     * @return
     */
    @RequestMapping(value = "/deviceUart/listByUserIdAndDeviceIdAndState", method = RequestMethod.POST)
    List<DeviceUart> uartListByUserIdAndDeviceIdAndState(@RequestParam("userId") Integer userId,
                                                         @RequestParam("userDeviceId") Integer userDeviceId,
                                                         @RequestParam("state") boolean state);
    //  ---------------  DeviceUart end   ---------------


//    //  ---------------  DeviceLog start   ---------------

    /**
     * 获得Gpio 日志
     *
     * @param page
     * @param pageSize
     * @param userGpioId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deviceLog/gpioList", method = RequestMethod.POST)
    IPage<DeviceGpioView> deviceGpioLogList(@RequestParam(defaultValue = "1", required = false) Integer page,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("userGpioId") Integer userGpioId,
                                           @RequestParam("userId") Integer userId);

    /**
     * 获得Uart日志
     *
     * @param page
     * @param pageSize
     * @param userUartId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deviceLog/deviceUartList", method = RequestMethod.POST)
    IPage<DeviceUartLogView> deviceUartLogList(@RequestParam(defaultValue = "1", required = false) Integer page,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("userUartId") Integer userUartId,
                                               @RequestParam("userId") Integer userId);

    /**
     * 添加日志
     *
     * @param deviceId
     * @param userId
     * @param gpioId
     * @param type     0上传 1下发
     * @param state
     * @return
     */
    @RequestMapping(value = "/deviceLog/addGpioLog", method = RequestMethod.POST)
    boolean addGpioLog(@RequestParam("deviceId") Integer deviceId,
                       @RequestParam("userId") Integer userId,
                       @RequestParam("gpioId") Integer gpioId,
                       @RequestParam("type") Integer type,
                       @RequestParam("state") Integer state);

    /**
     * @param deviceId
     * @param userId
     * @param uartId
     * @param type     0上传 1下发
     * @param data
     * @return
     */
    @RequestMapping(value = "/deviceLog/addUartLog", method = RequestMethod.POST)
    boolean addUartLog(@RequestParam("deviceId") Integer deviceId,
                       @RequestParam("userId") Integer userId,
                       @RequestParam("uartId") Integer uartId,
                       @RequestParam("type") Integer type,
                       @RequestParam("data") String data);
}
