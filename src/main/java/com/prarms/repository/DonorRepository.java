package com.prarms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.prarms.entity.Donor;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

	@Query("from Donor where donorName=:e")
	List<Donor> findDonorByName(@Param("e") String name);

	@Query("from Donor where donoremail=:e")
	Donor findByEmail(@Param("e") String email);

//	 List<Donor> findAllByDonationAmount();
//	select sum(colName) from Student
	@Query("select sum(donationAmount) from Donor")
	Double getTotalDonationAmount();

}
