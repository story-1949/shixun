package com.device.model;

public class Printer extends Device {
    private String model;
    
    public Printer(String id, String name, String model) {
        super(id, name);
        this.model = model;
    }
    
    @Override
    public String getType() { return "打印机"; }
    
    @Override
    public String getInfo() {
        return String.format("【%s】ID: %s, 名称: %s, 型号: %s, 状态: %s", 
            getType(), id, name, model, status);
    }
}
