package com.device.model;

public class Computer extends Device {
    private String cpu;
    
    public Computer(String id, String name, String cpu) {
        super(id, name);
        this.cpu = cpu;
    }
    
    @Override
    public String getType() { return "计算机"; }
    
    @Override
    public String getInfo() {
        return String.format("【%s】ID: %s, 名称: %s, CPU: %s, 状态: %s", 
            getType(), id, name, cpu, status);
    }
}
