package com.example.ipmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ipmanagement.entity.customers;

@Repository
public interface customerRepo extends JpaRepository<customers, Long> {

}
