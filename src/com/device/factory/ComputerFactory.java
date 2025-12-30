package com.device.factory;

import com.device.model.*;

public class ComputerFactory implements DeviceFactory {
    @Override
    public Device createDevice(String id, String name, String spec) {
        return new Computer(id, name, spec);
    }
}
