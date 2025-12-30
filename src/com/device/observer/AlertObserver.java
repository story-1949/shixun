package com.device.observer;

public class AlertObserver implements DeviceObserver {
    @Override
    public void update(String message) {
        if (message.contains("故障") || message.contains("异常")) {
            System.out.println("【告警】⚠️  " + message);
        }
    }
}
