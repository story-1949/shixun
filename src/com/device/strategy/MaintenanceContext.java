package com.device.strategy;

import com.device.model.Device;

public class MaintenanceContext {
    private MaintenanceStrategy strategy;
    
    public void setStrategy(MaintenanceStrategy strategy) {
        this.strategy = strategy;
    }
    
    public String executeMaintenance(Device device) {
        if (strategy == null) {
            return "未设置维护策略";
        }
        return strategy.performMaintenance(device);
    }
}
