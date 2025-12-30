package com.device.strategy;

import com.device.model.Device;

public class DeepMaintenanceStrategy implements MaintenanceStrategy {
    @Override
    public String performMaintenance(Device device) {
        return "对设备 [" + device.getName() + "] 执行深度维护：硬件检测、系统优化、性能测试";
    }
}
