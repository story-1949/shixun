package com.device.factory;

import com.device.model.*;

public class PrinterFactory implements DeviceFactory {
    @Override
    public Device createDevice(String id, String name, String spec) {
        return new Printer(id, name, spec);
    }
}
