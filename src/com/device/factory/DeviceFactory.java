package com.device.factory;

import com.device.model.*;

public interface DeviceFactory {
    Device createDevice(String id, String name, String spec);
}
