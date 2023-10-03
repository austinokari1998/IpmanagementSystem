package com.example.ipmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ipmanagement.entity.Ipaddress;

@Repository
public interface IpaddressRepo extends JpaRepository<Ipaddress, Long> {

    Ipaddress findByIpAddressAndStatus(String ipAddress, String status);

    List<Ipaddress> findByStatus(String string);

    @Query("SELECT ip FROM Ipaddress ip JOIN FETCH ip.customer WHERE ip.status = 'allocated'")
    List<Ipaddress> findAllocatedIpAddressesWithCustomerDetails();

    Ipaddress findFirstByStatusOrderByIpAddressAsc(String string);

}
