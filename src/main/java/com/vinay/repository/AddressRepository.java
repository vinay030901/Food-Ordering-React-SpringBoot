package com.vinay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinay.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
