package com.example.ipmanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_id")

    private Long id;
    @Column(name = "Customer_name")
    private String customer_name;
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference

    private List<Ipaddress> ipaddresses;

    public customers(Long id, String customer_name, String email) {
        this.id = id;
        this.customer_name = customer_name;
        this.email = email;

    }

    public customers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Ipaddress> getIpAddresses() {
        return ipaddresses;
    }

    public void setIpAddresses(List<Ipaddress> ipAddresses) {
        this.ipaddresses = ipAddresses;
    }

    @Override
    public String toString() {
        return "customers [customerId=" + id + ", customer_name=" + customer_name + ", email=" + email + "]";
    }
}
