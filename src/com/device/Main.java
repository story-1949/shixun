package com.device;

import com.device.ui.DeviceManagementUI;
import javax.swing.SwingUtilities;

/**
 * 设备管理系统主程序
 * 演示五种设计模式：
 * 1. 工厂模式 - 创建不同类型的设备
 * 2. 单例模式 - 设备管理器
 * 3. 装饰器模式 - 动态添加设备属性
 * 4. 策略模式 - 不同的维护策略
 * 5. 观察者模式 - 设备状态监控
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeviceManagementUI ui = new DeviceManagementUI();
            ui.setVisible(true);
        });
    }
}
