package com.device.model;

public class Server extends Device {
    private String ipAddress;
    
    public Server(String id, String name, String ipAddress) {
        super(id, name);
        this.ipAddress = ipAddress;
    }
    
    @Override
    public String getType() { return "服务器"; }
    
    @Override
    public String getInfo() {
        return String.format("【%s】ID: %s, 名称: %s, IP: %s, 状态: %s", 
            getType(), id, name, ipAddress, status);
    }
}
