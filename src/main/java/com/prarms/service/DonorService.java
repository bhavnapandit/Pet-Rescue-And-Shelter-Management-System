package com.prarms.service;

import java.util.List;

import com.prarms.dto.DonorDTO;
import com.prarms.entity.Donor;


public interface DonorService {
	// method used to save details
	DonorDTO saveDonor(Donor dnrDto);

	// method use to update donorPanel details
	DonorDTO updateDonor(int dnrId, Donor dnr);

	// method use to fetch all details
	List<DonorDTO> getAllDonor();

	// method to fetch details using Donor id
	DonorDTO getDonorById(int dnrId) ;

	// method to fetch DonorPanel details using name
	List<DonorDTO> getDonorByName(String name);

	// method use to fetch DonorPanel details using email
	DonorDTO getDonorByEmail(String email);

	//method to calculate entire donoation amount
	Double getTotalDonationAmount();
}