package com.device.singleton;

import com.device.model.Device;
import com.device.observer.DeviceObserver;
import java.util.*;

public class DeviceManager {
    private static volatile DeviceManager instance;
    private Map<String, Device> devices;
    private List<DeviceObserver> observers;
    
    private DeviceManager() {
        devices = new HashMap<>();
        observers = new ArrayList<>();
    }
    
    public static DeviceManager getInstance() {
        if (instance == null) {
            synchronized (DeviceManager.class) {
                if (instance == null) {
                    instance = new DeviceManager();
                }
            }
        }
        return instance;
    }
    
    public void addDevice(Device device) {
        devices.put(device.getId(), device);
        notifyObservers("添加设备: " + device.getName());
    }
    
    public Device getDevice(String id) {
        return devices.get(id);
    }
    
    public List<Device> getAllDevices() {
        return new ArrayList<>(devices.values());
    }
    
    public void updateDeviceStatus(String id, String status) {
        Device device = devices.get(id);
        if (device != null) {
            device.setStatus(status);
            notifyObservers("设备状态更新: " + device.getName() + " -> " + status);
        }
    }
    
    public void registerObserver(DeviceObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(DeviceObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(String message) {
        for (DeviceObserver observer : observers) {
            observer.update(message);
        }
    }
}
