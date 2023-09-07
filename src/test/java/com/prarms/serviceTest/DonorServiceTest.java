package com.prarms.serviceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prarms.entity.Address;

import com.prarms.entity.Donor;
import com.prarms.repository.DonorRepository;
import com.prarms.service.DonorService;


@SpringBootTest

public class DonorServiceTest {
	@Autowired
	DonorService dnrService;
	
	@MockBean
	DonorRepository dnrRepository;
	
	
	//positive test case
	@Test
	void testSaveDonor() {
		
		Address add = Address.builder().locality("Narayanpur").city("Tarakeswar").pincode(712410).
				state("West Bengal").build();
		
		Donor dnr= Donor.builder().donorName("Neha").donoremail("neha@gmail.com").contact("7067548010").
				donation("Food").donationAmount(5000).address(add).build();
		
		Mockito.when(dnrRepository.save(dnr)).thenReturn(dnr);
		assertEquals ("Neha",dnrService.saveDonor(dnr).getDonorName());
}
	
	//positive test case
	@Test
	void testGetDonorById() {
		
		Address add = Address.builder().locality("Narayanpur").city("Tarakeswar").pincode(712410).
				state("West Bengal").build();
		
		Donor dnr= Donor.builder().donorName("Neha").donoremail("neha@gmail.com").contact("7067548010").
				donation("Food").donationAmount(5000).address(add).build();
		Optional<Donor> opDnr =Optional.of(dnr);
		Mockito.when(dnrRepository.findById(dnr.getDonorId())).thenReturn(opDnr);
		
		assertTrue (dnrService.getDonorById(dnr.getDonorId()).getDonoremail().equals("neha@gmail.com"));
	}
	
	//negative test case
	@Test
	@DisplayName("Negative test case")
	void testNegativeDonorById()
	{
		Address add = Address.builder().locality("Narayanpur").city("Tarakeswar").pincode(712410).
				state("West Bengal").build();
		Donor dnr= Donor.builder().donorName("Neha").donoremail("neha@gmail.com").contact("7067548010").
				donation("Food").donationAmount(5000).address(add).build();
		Optional<Donor> opDnr =Optional.of(dnr);
		Mockito.when(dnrRepository.findById(dnr.getDonorId())).thenReturn(opDnr);
		
		assertTrue (dnrService.getDonorById(dnr.getDonorId()).getDonoremail().equals("jiya@gmail.com"));
	}
}