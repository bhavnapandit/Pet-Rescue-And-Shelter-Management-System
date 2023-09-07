package com.prarms.util;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.prarms.dto.PetDTO;
import com.prarms.entity.Pet;


@Component
public class PetConverter {
	//method to convert dto to pet entity
		public Pet convertDtoToPetEntity(PetDTO pDto)
		{
			Pet pet = new Pet();
			
			if(pDto!=null)
			{
				BeanUtils.copyProperties(pDto, pet);
			}
			
			return pet;
		}
		
		//method to convert pet entity to dto
		public PetDTO convertEntityToPetDto(Pet pet)
		{
			PetDTO pDto = new PetDTO();
			
			if(pet!=null)
			{
				BeanUtils.copyProperties(pet, pDto);
			}
			
			return pDto;
		}
}
