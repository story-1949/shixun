package com.device.ui;

import com.device.decorator.*;
import com.device.factory.*;
import com.device.model.Device;
import com.device.observer.*;
import com.device.singleton.DeviceManager;
import com.device.strategy.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * 美化的设备管理界面
 */
public class DeviceManagementUI extends JFrame {
    private DeviceManager deviceManager;
    private JTextArea outputArea;
    private MaintenanceContext maintenanceContext;
    
    // 现代化配色方案
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color DARK_BG = new Color(44, 62, 80);
    private static final Color LIGHT_BG = new Color(236, 240, 241);
    private static final Color TEXT_COLOR = new Color(52, 73, 94);
    
    public DeviceManagementUI() {
        deviceManager = DeviceManager.getInstance();
        maintenanceContext = new MaintenanceContext();
        
        // 注册观察者
        deviceManager.registerObserver(new LogObserver());
        deviceManager.registerObserver(new AlertObserver());
        
        // 设置现代化外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initUI();
    }
    
    private void initUI() {
        setTitle("🖥️ 设备管理系统 - 设计模式演示");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 主面板 - 使用渐变背景
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(LIGHT_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 顶部标题面板
        JPanel headerPanel = createHeaderPanel();
        
        // 中间控制面板
        JPanel controlPanel = createControlPanel();
        
        // 底部输出面板
        JPanel outputPanel = createOutputPanel();
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        mainPanel.add(outputPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("设备管理系统");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Design Patterns Demo - 五种设计模式实战演示");
        subtitleLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(189, 195, 199));
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(DARK_BG);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        
        headerPanel.add(textPanel, BorderLayout.WEST);
        
        return headerPanel;
    }
    
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        controlPanel.setBackground(LIGHT_BG);
        
        // 工厂模式和单例模式区域
        JPanel topSection = new JPanel(new GridLayout(1, 2, 15, 0));
        topSection.setBackground(LIGHT_BG);
        topSection.add(createFactoryPanel());
        topSection.add(createSingletonPanel());
        
        // 装饰器、策略和观察者模式区域
        JPanel bottomSection = new JPanel(new GridLayout(1, 3, 15, 0));
        bottomSection.setBackground(LIGHT_BG);
        bottomSection.add(createDecoratorPanel());
        bottomSection.add(createStrategyPanel());
        bottomSection.add(createObserverPanel());
        
        controlPanel.add(topSection);
        controlPanel.add(bottomSection);
        
        return controlPanel;
    }
    
    private JPanel createFactoryPanel() {
        JPanel panel = createStyledPanel("🏭 工厂模式", PRIMARY_COLOR);
        
        JButton addComputerBtn = createStyledButton("💻 添加计算机", new Color(52, 152, 219));
        JButton addServerBtn = createStyledButton("🖥️ 添加服务器", new Color(41, 128, 185));
        JButton addPrinterBtn = createStyledButton("🖨️ 添加打印机", new Color(30, 104, 161));
        
        addComputerBtn.addActionListener(e -> addComputer());
        addServerBtn.addActionListener(e -> addServer());
        addPrinterBtn.addActionListener(e -> addPrinter());
        
        panel.add(addComputerBtn);
        panel.add(Box.createVerticalStrut(8));
        panel.add(addServerBtn);
        panel.add(Box.createVerticalStrut(8));
        panel.add(addPrinterBtn);
        
        return panel;
    }
    
    private JPanel createSingletonPanel() {
        JPanel panel = createStyledPanel("🔒 单例模式", SUCCESS_COLOR);
        
        JButton showDevicesBtn = createStyledButton("📋 显示所有设备", new Color(39, 174, 96));
        JButton clearBtn = createStyledButton("🗑️ 清空输出", new Color(22, 160, 133));
        
        showDevicesBtn.addActionListener(e -> showAllDevices());
        clearBtn.addActionListener(e -> outputArea.setText(""));
        
        panel.add(showDevicesBtn);
        panel.add(Box.createVerticalStrut(8));
        panel.add(clearBtn);
        
        return panel;
    }
    
    private JPanel createDecoratorPanel() {
        JPanel panel = createStyledPanel("🎨 装饰器模式", new Color(155, 89, 182));
        
        JButton decorateBtn = createStyledButton("✨ 装饰设备", new Color(142, 68, 173));
        
        decorateBtn.addActionListener(e -> decorateDevice());
        
        panel.add(decorateBtn);
        
        return panel;
    }
    
    private JPanel createStrategyPanel() {
        JPanel panel = createStyledPanel("⚙️ 策略模式", WARNING_COLOR);
        
        JButton dailyBtn = createStyledButton("🔧 日常维护", new Color(243, 156, 18));
        JButton deepBtn = createStyledButton("🛠️ 深度维护", new Color(211, 84, 0));
        
        dailyBtn.addActionListener(e -> performMaintenance(new DailyMaintenanceStrategy()));
        deepBtn.addActionListener(e -> performMaintenance(new DeepMaintenanceStrategy()));
        
        panel.add(dailyBtn);
        panel.add(Box.createVerticalStrut(8));
        panel.add(deepBtn);
        
        return panel;
    }
    
    private JPanel createObserverPanel() {
        JPanel panel = createStyledPanel("👁️ 观察者模式", DANGER_COLOR);
        
        JButton updateStatusBtn = createStyledButton("🔄 更新状态", new Color(192, 57, 43));
        
        updateStatusBtn.addActionListener(e -> updateDeviceStatus());
        
        panel.add(updateStatusBtn);
        
        return panel;
    }
    
    private JPanel createStyledPanel(String title, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        titleLabel.setForeground(color);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // 鼠标悬停效果
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private JPanel createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(Color.WHITE);
        outputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TEXT_COLOR, 1),
                "📊 系统输出日志",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Microsoft YaHei", Font.BOLD, 14),
                TEXT_COLOR
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        outputArea.setBackground(new Color(250, 250, 250));
        outputArea.setForeground(TEXT_COLOR);
        outputArea.setLineWrap(false);
        outputArea.setWrapStyleWord(false);
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        scrollPane.setBorder(null);
        
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        
        // 欢迎信息
        appendOutput("═══════════════════════════════════════════════════════════════════════════════");
        appendOutput("  🎉 欢迎使用设备管理系统！");
        appendOutput("  📚 本系统演示了五种经典设计模式的实际应用");
        appendOutput("  👆 请点击上方按钮开始操作");
        appendOutput("═══════════════════════════════════════════════════════════════════════════════\n");
        
        return outputPanel;
    }
    
    private void addComputer() {
        int count = deviceManager.getAllDevices().size() + 1;
        DeviceFactory factory = new ComputerFactory();
        Device device = factory.createDevice("C" + String.format("%03d", count), "办公电脑-" + count, "Intel i7");
        deviceManager.addDevice(device);
        appendOutput("💻 [工厂模式] 创建计算机成功");
        appendOutput("   ├─ 设备ID: " + device.getId());
        appendOutput("   ├─ 设备名称: " + device.getName());
        appendOutput("   ├─ CPU型号: Intel i7");
        appendOutput("   └─ 当前状态: " + device.getStatus() + "\n");
    }
    
    private void addPrinter() {
        int count = deviceManager.getAllDevices().size() + 1;
        DeviceFactory factory = new PrinterFactory();
        Device device = factory.createDevice("P" + String.format("%03d", count), "激光打印机-" + count, "HP LaserJet");
        deviceManager.addDevice(device);
        appendOutput("🖨️ [工厂模式] 创建打印机成功");
        appendOutput("   ├─ 设备ID: " + device.getId());
        appendOutput("   ├─ 设备名称: " + device.getName());
        appendOutput("   ├─ 打印机型号: HP LaserJet");
        appendOutput("   └─ 当前状态: " + device.getStatus() + "\n");
    }
    
    private void addServer() {
        int count = deviceManager.getAllDevices().size() + 1;
        DeviceFactory factory = new ServerFactory();
        Device device = factory.createDevice("S" + String.format("%03d", count), "Web服务器-" + count, "192.168.1." + (100 + count));
        deviceManager.addDevice(device);
        appendOutput("🖥️ [工厂模式] 创建服务器成功");
        appendOutput("   ├─ 设备ID: " + device.getId());
        appendOutput("   ├─ 设备名称: " + device.getName());
        appendOutput("   ├─ IP地址: 192.168.1." + (100 + count));
        appendOutput("   └─ 当前状态: " + device.getStatus() + "\n");
    }
    
    private void showAllDevices() {
        if (deviceManager.getAllDevices().isEmpty()) {
            appendOutput("⚠️  暂无设备，请先添加设备！\n");
            return;
        }
        
        appendOutput("┌─────────────────────────────────────────────────────────────────────────────┐");
        appendOutput("│  📋 所有设备列表（单例模式管理）                                              │");
        appendOutput("├─────────────────────────────────────────────────────────────────────────────┤");
        int index = 1;
        for (Device device : deviceManager.getAllDevices()) {
            String icon = device.getType().equals("计算机") ? "💻" : 
                         device.getType().equals("服务器") ? "🖥️" : "🖨️";
            appendOutput("│  " + index + ". " + icon + " " + device.getInfo());
            index++;
        }
        appendOutput("├─────────────────────────────────────────────────────────────────────────────┤");
        appendOutput("│  📊 设备总数: " + deviceManager.getAllDevices().size() + " 台");
        appendOutput("└─────────────────────────────────────────────────────────────────────────────┘\n");
    }
    
    private void decorateDevice() {
        if (deviceManager.getAllDevices().isEmpty()) {
            appendOutput("⚠️  请先添加设备！\n");
            return;
        }
        
        Device device = deviceManager.getAllDevices().get(0);
        Device decorated = new WarrantyDecorator(device, "3年");
        decorated = new LocationDecorator(decorated, "3楼机房");
        
        appendOutput("┌─────────────────────────────────────────────────────────────────────────────┐");
        appendOutput("│  🎨 装饰器模式演示                                                            │");
        appendOutput("├─────────────────────────────────────────────────────────────────────────────┤");
        appendOutput("│  📦 原始设备:");
        appendOutput("│     " + device.getInfo());
        appendOutput("│");
        appendOutput("│  ✨ 装饰后（动态添加属性）:");
        appendOutput("│     " + decorated.getInfo());
        appendOutput("│");
        appendOutput("│  💡 说明: 装饰器模式允许在不修改原对象的情况下动态添加新功能");
        appendOutput("└─────────────────────────────────────────────────────────────────────────────┘\n");
    }
    
    private void performMaintenance(MaintenanceStrategy strategy) {
        if (deviceManager.getAllDevices().isEmpty()) {
            appendOutput("⚠️  请先添加设备！\n");
            return;
        }
        
        maintenanceContext.setStrategy(strategy);
        String strategyName = strategy instanceof DailyMaintenanceStrategy ? "日常维护策略" : "深度维护策略";
        String icon = strategy instanceof DailyMaintenanceStrategy ? "🔧" : "🛠️";
        
        appendOutput("┌─────────────────────────────────────────────────────────────────────────────┐");
        appendOutput("│  ⚙️ 策略模式演示 - " + strategyName + "                                        │");
        appendOutput("├─────────────────────────────────────────────────────────────────────────────┤");
        for (Device device : deviceManager.getAllDevices()) {
            String result = maintenanceContext.executeMaintenance(device);
            appendOutput("│  " + icon + " " + result);
        }
        appendOutput("│");
        appendOutput("│  💡 说明: 策略模式允许在运行时选择不同的算法或行为");
        appendOutput("└─────────────────────────────────────────────────────────────────────────────┘\n");
    }
    
    private void updateDeviceStatus() {
        if (deviceManager.getAllDevices().isEmpty()) {
            appendOutput("⚠️  请先添加设备！");
            return;
        }
        
        String[] statuses = {"正常", "维护中", "故障", "离线"};
        String[] icons = {"✅", "🔧", "❌", "📴"};
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[statuses.length];
        
        for (int i = 0; i < statuses.length; i++) {
            radioButtons[i] = new JRadioButton(icons[i] + " " + statuses[i]);
            radioButtons[i].setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
            group.add(radioButtons[i]);
            panel.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "👁️ 观察者模式 - 选择设备状态",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String selectedStatus = null;
            String selectedIcon = null;
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    selectedStatus = statuses[i];
                    selectedIcon = icons[i];
                    break;
                }
            }
            
            if (selectedStatus != null) {
                Device device = deviceManager.getAllDevices().get(0);
                deviceManager.updateDeviceStatus(device.getId(), selectedStatus);
                appendOutput("┌─────────────────────────────────────────────────────────────────────────────┐");
                appendOutput("│  👁️ 观察者模式演示                                                           │");
                appendOutput("├─────────────────────────────────────────────────────────────────────────────┤");
                appendOutput("│  " + selectedIcon + " 设备名称: " + device.getName());
                appendOutput("│  🔄 状态变更: " + device.getStatus() + " → " + selectedStatus);
                appendOutput("│");
                appendOutput("│  📢 通知结果:");
                appendOutput("│     ✓ 日志观察者已记录状态变更");
                appendOutput("│     ✓ 告警观察者已检查并处理");
                appendOutput("│");
                appendOutput("│  💡 说明: 观察者模式实现了对象间的一对多依赖关系");
                appendOutput("└─────────────────────────────────────────────────────────────────────────────┘\n");
            }
        }
    }
    
    private void appendOutput(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
}
