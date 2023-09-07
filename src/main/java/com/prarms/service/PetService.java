package com.prarms.service;

import java.util.List;

import com.prarms.dto.PetDTO;
import com.prarms.entity.Pet;

public interface PetService {

	// method to save pet details in database
	PetDTO savePet(Pet pet);

	// method to get all the details of pet present in database
	List<PetDTO> getAllDetails();

	// method to update pet details
	PetDTO updatePet(Pet pet, int pId);

	// method to get pet by id
	PetDTO getPetById(int id);

	// method to get pets by name
	List<PetDTO> getPetByName(String name);

	// method to get pets by breed
	List<PetDTO> getPetByBreed(String breed);

	// method to get pets by category
	List<PetDTO> getPetByCategory(String category);

	// method to count number of pets
	long countOfPet();

	// method to sort pets by name
	List<PetDTO> sortPetByName();
	
	//method to get pet which are available
	List<PetDTO> getPetByAvalibility(String available);

	
}
