import com.device.decorator.*;
import com.device.factory.*;
import com.device.model.Device;
import com.device.observer.*;
import com.device.singleton.DeviceManager;
import com.device.strategy.*;

/**
 * 控制台演示程序 - 展示五种设计模式
 */
public class TestDemo {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    设备管理系统 - 设计模式演示");
        System.out.println("========================================\n");
        
        // 获取单例管理器
        DeviceManager manager = DeviceManager.getInstance();
        
        // 注册观察者
        manager.registerObserver(new LogObserver());
        manager.registerObserver(new AlertObserver());
        
        System.out.println("【1. 工厂模式演示】");
        System.out.println("-------------------");
        DeviceFactory computerFactory = new ComputerFactory();
        Device computer = computerFactory.createDevice("C001", "办公电脑", "Intel i7");
        manager.addDevice(computer);
        
        DeviceFactory serverFactory = new ServerFactory();
        Device server = serverFactory.createDevice("S001", "Web服务器", "192.168.1.100");
        manager.addDevice(server);
        
        DeviceFactory printerFactory = new PrinterFactory();
        Device printer = printerFactory.createDevice("P001", "激光打印机", "HP LaserJet");
        manager.addDevice(printer);
        
        System.out.println("\n【2. 单例模式演示】");
        System.out.println("-------------------");
        System.out.println("验证单例: " + (manager == DeviceManager.getInstance()));
        System.out.println("设备总数: " + manager.getAllDevices().size());
        
        System.out.println("\n【3. 装饰器模式演示】");
        System.out.println("-------------------");
        System.out.println("原始设备: " + computer.getInfo());
        Device decorated = new WarrantyDecorator(computer, "3年");
        decorated = new LocationDecorator(decorated, "3楼机房");
        System.out.println("装饰后: " + decorated.getInfo());
        
        System.out.println("\n【4. 策略模式演示】");
        System.out.println("-------------------");
        MaintenanceContext context = new MaintenanceContext();
        
        context.setStrategy(new DailyMaintenanceStrategy());
        System.out.println(context.executeMaintenance(computer));
        
        context.setStrategy(new DeepMaintenanceStrategy());
        System.out.println(context.executeMaintenance(server));
        
        System.out.println("\n【5. 观察者模式演示】");
        System.out.println("-------------------");
        manager.updateDeviceStatus("S001", "故障");
        manager.updateDeviceStatus("C001", "维护中");
        
        System.out.println("\n========================================");
        System.out.println("演示完成！所有设计模式已成功展示。");
        System.out.println("========================================");
    }
}
