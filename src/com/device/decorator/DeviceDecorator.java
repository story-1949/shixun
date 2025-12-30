package com.device.decorator;

import com.device.model.Device;

public abstract class DeviceDecorator extends Device {
    protected Device device;
    
    public DeviceDecorator(Device device) {
        super(device.getId(), device.getName());
        this.device = device;
        this.status = device.getStatus();
    }
    
    @Override
    public String getType() {
        return device.getType();
    }
    
    @Override
    public abstract String getInfo();
}
