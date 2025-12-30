package com.device.strategy;

import com.device.model.Device;

public class DailyMaintenanceStrategy implements MaintenanceStrategy {
    @Override
    public String performMaintenance(Device device) {
        return "对设备 [" + device.getName() + "] 执行日常维护：清洁、检查运行状态";
    }
}
