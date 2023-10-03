package com.example.ipmanagement.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.ipmanagement.Errorhandling.CustomerNotFoundException;
import com.example.ipmanagement.Errorhandling.IPAllocationException;
import com.example.ipmanagement.Errorhandling.IPNotFoundException;
import com.example.ipmanagement.entity.Ipaddress;
import com.example.ipmanagement.entity.IpaddressDTO;
import com.example.ipmanagement.entity.customers;
import com.example.ipmanagement.service.Ipaddressservice;

@Controller
@RequestMapping(path = "/ip")

public class customerIpaddcontroller {
    @Autowired
    private Ipaddressservice ipAddressService;

    // persIST USER FIRST BEFORE ALLOCATING THE IP ADDRESS
    // @PostMapping("/addcustomer")
    // public ResponseEntity<customers> addCustomer(@RequestBody customers customer)
    // {
    // try {
    // customers savedCustomer = ipAddressService.;
    // return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    // } catch (Exception e) {
    // // Handle any exceptions and return an appropriate response
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PostMapping(path = "/allocate")
    public ResponseEntity<Ipaddress> allocateIpAddress(@RequestBody customers customer) {
        try {
            Ipaddress allocatedIpAddress = ipAddressService.allocateIpAddress(customer);
            return new ResponseEntity<>(allocatedIpAddress, HttpStatus.CREATED);
        } catch (IPAllocationException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IPNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/release/{ipAddress}")
    public ResponseEntity<Void> releaseIpAddress(@PathVariable String ipAddress) {
        try {
            ipAddressService.releaseIpAddress(ipAddress);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IPNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IPAllocationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/allocate-batch")
    public ResponseEntity<List<Ipaddress>> allocateIpAddressBatch(@RequestParam int numberOfAddresses) {
        List<Ipaddress> allocatedAddresses = ipAddressService.allocateIpAddressBatch(numberOfAddresses);
        return new ResponseEntity<>(allocatedAddresses, HttpStatus.CREATED);
    }

    @GetMapping("/allocated")
    public ResponseEntity<List<IpaddressDTO>> listAllocatedIpAddresses() {
        List<IpaddressDTO> result = ipAddressService.listAllocatedIpAddresses().stream()
                .map(ipAddress -> new IpaddressDTO(
                        ipAddress.getIpAddress(),
                        ipAddress.getStatus(),
                        ipAddress.getCustomer().getCustomer_name(),
                        ipAddress.getCustomer().getEmail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Ipaddress>> listAvailableIpAddresses() {
        List<Ipaddress> availableIpAddresses = ipAddressService.listAvailableIpAddresses();
        return new ResponseEntity<>(availableIpAddresses, HttpStatus.OK);
    }
}
