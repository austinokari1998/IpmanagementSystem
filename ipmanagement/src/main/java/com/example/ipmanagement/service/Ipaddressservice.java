package com.example.ipmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ipmanagement.Errorhandling.IPAllocationException;
import com.example.ipmanagement.Errorhandling.IPNotFoundException;
import com.example.ipmanagement.entity.Ipaddress;
import com.example.ipmanagement.entity.customers;
import com.example.ipmanagement.repository.IpaddressRepo;
import com.example.ipmanagement.repository.customerRepo;

@Service
public class Ipaddressservice {
    @Autowired
    private IpaddressRepo ipAddressRepository;
    @Autowired
    private customerRepo customerrepository;

    public List<Ipaddress> allocateIpAddressBatch(int numberOfAddresses) {
        List<Ipaddress> allocatedAddresses = new ArrayList<>();

        for (int i = 1; i <= numberOfAddresses; i++) {
            Ipaddress ipAddress = new Ipaddress();
            ipAddress.setIpAddress("192.168.1." + i); // Set the IP address format
            ipAddress.setStatus("available"); // You can set the initial status here
            // Save the IP address to the database
            ipAddressRepository.save(ipAddress);
            allocatedAddresses.add(ipAddress);
        }

        return allocatedAddresses;
    }

    // SAVE THE CUSTOMER FIRST....
    public Ipaddress allocateIpAddress(customers customer) {
        // Check if there is an available IP address
        Ipaddress ipAddress = ipAddressRepository.findFirstByStatusOrderByIpAddressAsc("available");
        customers savedcustomer = customerrepository.save(customer);
        if (ipAddress != null) {
            // Allocate the IP address
            ipAddress.setStatus("allocated");
            ipAddress.setCustomer(savedcustomer);
            return ipAddressRepository.save(ipAddress);
        } else {
            throw new IPAllocationException("No available IP addresses");
        }
    }

    public void releaseIpAddress(String ipAddress) {
        // Find the allocated IP address to release
        Ipaddress allocatedIpAddress = ipAddressRepository.findByIpAddressAndStatus(ipAddress, "allocated");

        if (allocatedIpAddress != null) {
            // Release the IP address
            allocatedIpAddress.setStatus("available");
            allocatedIpAddress.setCustomer(null);
            ipAddressRepository.save(allocatedIpAddress);
        } else {
            throw new IPNotFoundException("IP address not found or not allocated");
        }
    }

    public List<Ipaddress> listAllocatedIpAddresses() {
        return ipAddressRepository.findAllocatedIpAddressesWithCustomerDetails();
    }

    public List<Ipaddress> listAvailableIpAddresses() {
        return ipAddressRepository.findByStatus("available");
    }

}
