package com.device.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogObserver implements DeviceObserver {
    private static final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public void update(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[日志] " + timestamp + " - " + message);
    }
}
