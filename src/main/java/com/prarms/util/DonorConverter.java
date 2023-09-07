package com.prarms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.prarms.dto.DonorDTO;
import com.prarms.entity.Donor;



@Component
public class DonorConverter {
	
	
		
		
	//method to convert dto to DonorPanel entity
		public Donor convertDtoToDnrEntity(DonorDTO dnrDto)
		{
		Donor dnr = new Donor();
		if(dnrDto!=null)
		 {
			BeanUtils.copyProperties(dnrDto, dnr);
		}
		
		return dnr;
		
		}
		//method to convert  DonorPanel entity to dto
		public DonorDTO convertEntityTodnrdDto(Donor dnr)
		{
			
			DonorDTO dnrDto= new DonorDTO();
			if(dnr!=null) {
				BeanUtils.copyProperties(dnr, dnrDto);
			}
			return dnrDto;
		}
		
	}