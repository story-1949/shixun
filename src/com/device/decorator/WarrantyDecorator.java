package com.device.decorator;

import com.device.model.Device;

public class WarrantyDecorator extends DeviceDecorator {
    private String warrantyPeriod;
    
    public WarrantyDecorator(Device device, String warrantyPeriod) {
        super(device);
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    public String getInfo() {
        return device.getInfo() + " [保修期: " + warrantyPeriod + "]";
    }
}
