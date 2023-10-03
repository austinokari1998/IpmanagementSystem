package com.example.ipmanagement.entity;

public class IpaddressDTO {

    private String ipAddress;
    private String status;
    private String customer_name;
    private String email;

    public IpaddressDTO(String ipAddress, String status, String customer_name, String email) {
        this.ipAddress = ipAddress;
        this.status = status;
        this.customer_name = customer_name;
        this.email = email;
    }

    public IpaddressDTO() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}