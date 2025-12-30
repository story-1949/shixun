package com.device.model;

public abstract class Device {
    protected String id;
    protected String name;
    protected String status;
    
    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "正常";
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public abstract String getType();
    public abstract String getInfo();
}
