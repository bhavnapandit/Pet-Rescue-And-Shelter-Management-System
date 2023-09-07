package com.prarms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prarms.dto.PetDTO;
import com.prarms.entity.Pet;
import com.prarms.repository.PetRespository;
import com.prarms.repository.UserRepository;
import com.prarms.service.PetService;
import com.prarms.util.PetConverter;
import com.prarms.exceptions.DataNotFoundException;
import com.prarms.exceptions.ResourceNotFoundException;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	PetRespository petRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PetConverter petConverter;

	@Override
	public PetDTO savePet(Pet pet) {
		petRepository.save(pet); // saving the object to the database

		PetDTO pDto = petConverter.convertEntityToPetDto(pet);
		return pDto;

	}

	@Override
	public List<PetDTO> getAllDetails() throws DataNotFoundException {
		List<Pet> pets = petRepository.findAll();// to fetch all pet details

		// if pet list is empty it will throw an error
		if (pets.isEmpty()) {
			throw new DataNotFoundException("Not data found in pet tables!!");
		}
		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));// converting pet into dto
		}
		return pDtos;

	}

	@Override
	public PetDTO updatePet(Pet pet, int pId) throws ResourceNotFoundException {
		// fetch the pet details using the id and throw an error if that id is not found
		Pet existPet = petRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Pet", "id", "pId"));

		// updating the existing pet details with new updated details
		existPet.setName(pet.getName());
		existPet.setBreed(pet.getBreed());
		existPet.setDateOfRescue(pet.getDateOfRescue());
		existPet.setAvailable(pet.getAvailable());
		existPet.setFund(pet.getFund());

		// saving the changes made
		petRepository.save(existPet);

		return petConverter.convertEntityToPetDto(existPet);
	}

	@Override
	public PetDTO getPetById(int id) throws ResourceNotFoundException {
		// fetch the pet details using the id and throw an error if that id is not found
		Pet Pet = petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet", "id", "pId"));

		// converting the entity to dto
		return petConverter.convertEntityToPetDto(Pet);
	}

	@Override
	public List<PetDTO> getPetByName(String name) throws DataNotFoundException {
		List<Pet> pets = petRepository.findPetByName(name);
		// if pets list is empty it will throw an error
		if (pets.isEmpty()) {
			throw new DataNotFoundException("Not data with this name found in pet tables!!");
		}
		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));
		}
		return pDtos;
	}

	@Override
	public List<PetDTO> getPetByBreed(String breed) throws DataNotFoundException {
		// fetching details using breed of pet
		List<Pet> pets = petRepository.findPetByBreed(breed);
		// if pets list is empty it will throw an error
		if (pets.isEmpty()) {
			throw new DataNotFoundException("Not data with this breed is found in pet tables!!");
		}

		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));
		}
		return pDtos;
	}

	@Override
	public List<PetDTO> getPetByCategory(String category) throws DataNotFoundException {
		// fetching details using category of pet
		List<Pet> pets = petRepository.findPetByCategory(category);

		// if pets list is empty it will throw an error
		if (pets.isEmpty()) {
			throw new DataNotFoundException("Not data in this category is found in pet tables!!");
		}
		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));
		}
		return pDtos;
	}

	@Override
	public long countOfPet() {
		// with the help of count method fetching the number of pet present in database
		long count = petRepository.count();
		return count;
	}

	@Override
	public List<PetDTO> sortPetByName() {
		// sorting pet according to name
		List<Pet> pets = petRepository.findAllSortedByName();
		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));
		}
		return pDtos;
	}

	@Override
	public List<PetDTO> getPetByAvalibility(String available) throws DataNotFoundException {
		// fetching details using category of pet
		List<Pet> pets = petRepository.findPetByAvailable(available);

		// if pets list is empty it will throw an error
		if (pets.isEmpty()) {
			throw new DataNotFoundException("Not data in this category is found in pet tables!!");
		}
		List<PetDTO> pDtos = new ArrayList<>();

		for (Pet p : pets) {

			pDtos.add(petConverter.convertEntityToPetDto(p));
		}
		return pDtos;

	}
}
