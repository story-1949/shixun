package com.device.factory;

import com.device.model.*;

public class ServerFactory implements DeviceFactory {
    @Override
    public Device createDevice(String id, String name, String spec) {
        return new Server(id, name, spec);
    }
}
