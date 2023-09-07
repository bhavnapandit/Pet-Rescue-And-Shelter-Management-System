package com.prarms.serviceTest;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prarms.entity.Address;
import com.prarms.entity.Pet;
import com.prarms.entity.User;
import com.prarms.repository.PetRespository;
import com.prarms.repository.UserRepository;
import com.prarms.service.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;
	

	@MockBean
	UserRepository userRepository;
	
	@MockBean
	PetRespository petRespository;

	//Positive Test Case
	
	@Test
	@DisplayName("TestSaveUser")
	void testSaveUser() {

		Address address = Address.builder().city("Kolkata").locality("Kolkata").state("West Bengal").pincode(700023)
				.build();
		User user = User.builder().name("Mimi Das").email("mimi@gmail.com").address(address).build();

		Mockito.when(userRepository.save(user)).thenReturn(user);

		assertEquals("Mimi Das", userService.saveUser(user).getName());
	}

	//Positive Test Case
	
	@Test
	@DisplayName("GetUserById")
	void testGetUserById() {

		Address address = Address.builder().city("Kolkata").locality("Kolkata").
				state("West Bengal").pincode(700023).build();
		User user = User.builder().name("Mimi Das").email("mimi@gmail.com").
				address(address).build();

		Optional<User> opUser = Optional.of(user);
		Mockito.when(userRepository.findById(user.getId())).thenReturn(opUser);

		String email = userService.getUserById(user.getId()).getEmail();
		assertTrue(email.equals("mimi@gmail.com"));
	}
	
	//Positive Test Case
	
	@Test
	@DisplayName("AssignPetToUser")
	void testAssignPetToUser() {

		Address address = Address.builder().city("Kolkata").locality("Kolkata").
				state("West Bengal").pincode(700023).build();
		
		User user = User.builder().name("Mimi Das").email("mimi@gmail.com").
				address(address).build();
		
		Pet pet = Pet.builder().name("Sam").breed("Pug").category("Dog").dateOfRescue(LocalDate.of(2001, 9, 22))
				.available("not adopted").fund(600).build();
		
		
		Optional<Pet> opPet = Optional.of(pet);
		Mockito.when(petRespository.findById(pet.getId())).thenReturn(opPet);

		Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		
		userService.assignUserToPet(user.getId(),pet.getId());
		
		assertEquals(pet.getUser().getName(),userService.getUserById(user.getId()).getName());
	}

	//Negative Test Case

	@Test
	@DisplayName("Negative Test Case")
	void testNegativeGetUserById() {

		Address address = Address.builder().city("Kolkata").locality("Kolkata").
				state("West Bengal").pincode(700023).build();
		User user = User.builder().name("Mimi Das").email("mimi@gmail.com").
				address(address).build();

		Optional<User> opUser = Optional.of(user);
		Mockito.when(userRepository.findById(user.getId())).thenReturn(opUser);

		String email = userService.getUserById(user.getId()).getEmail();
		assertTrue(email.equals("ankana@gmail.com"));
	}
}