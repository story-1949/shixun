package com.device.decorator;

import com.device.model.Device;

public class LocationDecorator extends DeviceDecorator {
    private String location;
    
    public LocationDecorator(Device device, String location) {
        super(device);
        this.location = location;
    }
    
    @Override
    public String getInfo() {
        return device.getInfo() + " [位置: " + location + "]";
    }
}
