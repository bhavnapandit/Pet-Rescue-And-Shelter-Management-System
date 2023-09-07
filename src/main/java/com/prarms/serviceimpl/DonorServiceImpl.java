package com.prarms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.prarms.dto.DonorDTO;
import com.prarms.entity.Donor;
import com.prarms.repository.AddressRepository;
import com.prarms.repository.DonorRepository;
import com.prarms.service.DonorService;
import com.prarms.util.DonorConverter;
import com.prarms.exceptions.DataNotFoundException;
import com.prarms.exceptions.ResourceNotFoundException;

@Service
public class DonorServiceImpl implements DonorService {

	@Autowired
	DonorRepository dnrRepository;

	@Autowired
	DonorConverter dnrConverter;
	
	@Autowired
	AddressRepository addRepository;

	@Override
	public DonorDTO saveDonor(Donor dnr)throws DataIntegrityViolationException {
		Donor existDnr=dnrRepository.findByEmail(dnr.getDonoremail());
		
		//if same email or contact is added
		if(existDnr!=null) {
			throw new DataIntegrityViolationException("Invalid Email or contact");
		}
//		System.out.println(dnr.getAddress());
		addRepository.save(dnr.getAddress());
		dnrRepository.save(dnr); // saving the object to the database

		//coverting to dto
		DonorDTO dDto = dnrConverter.convertEntityTodnrdDto(dnr);
		return dDto;

	}

	@Override
	public DonorDTO updateDonor(int dnrId, Donor dnr) {
		Donor existDnr = dnrRepository.findById(dnrId)
				.orElseThrow(() -> new ResourceNotFoundException("DonorPanel", "id", "dnrId"));

		// updating the existing student details with new updated details
		existDnr.setDonorName(dnr.getDonorName());
		existDnr.setDonoremail(dnr.getDonoremail());
		existDnr.setContact(dnr.getContact());
		existDnr.setDonation(dnr.getDonation());
		existDnr.setDonationAmount(dnr.getDonationAmount());
		existDnr.setAddress(dnr.getAddress());

		// saving the changes made
		dnrRepository.save(existDnr);
		return dnrConverter.convertEntityTodnrdDto(existDnr);
	}

	@Override
	public List<DonorDTO> getAllDonor() throws DataNotFoundException{

		List<Donor> Donors = dnrRepository.findAll();
		List<DonorDTO> dnrDtos = new ArrayList<>();

		//if not data is present in donor table
		if(Donors.isEmpty()) {
			throw new DataNotFoundException("No data found in Donor tables!!");
		}
		for (Donor s : Donors) {
			//converting into dto
			dnrDtos.add(dnrConverter.convertEntityTodnrdDto(s));
		}
		return dnrDtos;
	}

	@Override
	public DonorDTO getDonorById(int dnrId) throws ResourceNotFoundException {
		//if donor with that id is not found it will throw an error
		Donor dnr = dnrRepository.findById(dnrId)
				.orElseThrow(() -> new ResourceNotFoundException("DonorPanel", "id", dnrId));
		return dnrConverter.convertEntityTodnrdDto(dnr);

	}
	
	@Override
	public List<DonorDTO> getDonorByName(String name) throws DataNotFoundException{
		List<Donor> donors= dnrRepository.findDonorByName(name);
		
		//if no donor with that name is found it will throw error
		if(donors.isEmpty()) {
			
			 throw new DataNotFoundException("Donor with this name is not present!!");
		}
		List<DonorDTO> dnrDtos= new ArrayList<>();
		for(Donor s: donors)
		{
			dnrDtos.add(dnrConverter.convertEntityTodnrdDto(s));
		}
		return dnrDtos;
		
	}

	@Override
	public DonorDTO getDonorByEmail(String email) {
		//fetching data using query method in donor repository
		Donor dnr= dnrRepository.findByEmail(email);
		return dnrConverter.convertEntityTodnrdDto(dnr);
	}

	@Override
	public Double getTotalDonationAmount() {
		return dnrRepository.getTotalDonationAmount();
	}
	
	


}