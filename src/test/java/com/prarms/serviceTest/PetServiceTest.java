package com.prarms.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prarms.entity.Pet;
import com.prarms.repository.PetRespository;
import com.prarms.service.PetService;

@SpringBootTest
public class PetServiceTest {

	@Autowired
	PetService petService;

	@MockBean
	PetRespository petRespository;

	//positive test case
	@Test
	void testSavePet() {
		Pet pet = Pet.builder().name("Sam").breed("Pug").category("Dog").dateOfRescue(LocalDate.of(2001, 9, 22))
				.available("not adopted").fund(600).build();
		
		Mockito.when(petRespository.save(pet)).thenReturn(pet);
		
		//testing 
		assertEquals("Sam",petService.savePet(pet).getName());
	}
	
	
	//positive test case
	@Test
	void testGetPetById() {
		Pet pet = Pet.builder().name("Sam").breed("Pug").category("Dog").dateOfRescue(LocalDate.of(2001, 9, 22))
				.available("not adopted").fund(600).build();
		Optional<Pet> oPet=Optional.of(pet);
		Mockito.when(petRespository.findById(pet.getId())).thenReturn(oPet);
		
		assertTrue(petService.getPetById(pet.getId()).getName().equals("Sam"));
	}
	
	//negative test case
	@Test
	@DisplayName("Negative test case")
	void testNegativePetById() {
		Pet pet = Pet.builder().name("Sam").breed("Pug").category("Dog").dateOfRescue(LocalDate.of(2001, 9, 22))
				.available("not adopted").fund(600).build();
		Optional<Pet> oPet=Optional.of(pet);
		Mockito.when(petRespository.findById(pet.getId())).thenReturn(oPet);
		
		assertTrue(petService.getPetById(pet.getId()).getName().equals("max"));
	}
	
	
	
	
	
}
