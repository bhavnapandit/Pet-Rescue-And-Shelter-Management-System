package com.prarms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prarms.entity.Address;


public interface  AddressRepository extends JpaRepository<Address, Integer>{

	
	
}
