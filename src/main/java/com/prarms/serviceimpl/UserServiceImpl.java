package com.prarms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.prarms.dto.UserDTO;
import com.prarms.entity.Pet;
import com.prarms.entity.User;
import com.prarms.exceptions.DataNotFoundException;
import com.prarms.exceptions.ResourceNotFoundException;
import com.prarms.repository.AddressRepository;
import com.prarms.repository.PetRespository;
import com.prarms.repository.UserRepository;
import com.prarms.service.UserService;
import com.prarms.util.UserConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConverter userConverter;

	@Autowired
	PetRespository petRepository;

	//
	@Override
	public UserDTO saveUser(User usr) {
		String user = usr.getEmail().substring(0, usr.getEmail().lastIndexOf("@"));
		usr.setUserName(user);
		
		addressRepository.save(usr.getAddress());
		userRepository.save(usr);// saving the object to the database

		UserDTO uDTo = userConverter.convertEntityToUsrDto(usr);
		return uDTo;

	}

	@Override
	public List<UserDTO> getAllDetails() {
		List<User> users = userRepository.findAll();// to fetch all user details

		List<UserDTO> uDtos = new ArrayList<>();

		for (User u : users) {

			uDtos.add(userConverter.convertEntityToUsrDto(u));// converting user into dto
		}
		return uDtos;

	}

	@Override
	public UserDTO updateUser(User user, int uId) throws ResourceNotFoundException {
		// fetch the user details using the id and throw an error if that id is not found
		User existUser = userRepository.findById(uId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", "uId"));

		// updating the existing user details with new updated details
		System.out.println(user.getAddress());
		existUser.setName(user.getName());
		existUser.setEmail(user.getEmail());
		existUser.setUserName(user.getUserName());
		existUser.setAddress(user.getAddress());
		
		// saving the changes made
		userRepository.save(existUser);

		return userConverter.convertEntityToUsrDto(existUser);
	}

	@Override
	public UserDTO getUserById(int id) throws ResourceNotFoundException {
		// fetch the user details using the id and throw an error if that id is not found
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", "uId"));

		// converting the entity to dto
		return userConverter.convertEntityToUsrDto(user);
	}

	@Override
	public List<UserDTO> getUserByName(String name) {
		List<User> users = userRepository.findUserByName(name);
		List<UserDTO> uDtos = new ArrayList<>();

		for (User u : users) {

			uDtos.add(userConverter.convertEntityToUsrDto(u));
		}
		return uDtos;
	}

	@Override
	public List<UserDTO> getUserByEmail(String email) {
		// fetching details using email of user
		List<User> users = userRepository.findUserByEmail(email);
		List<UserDTO> uDtos = new ArrayList<>();

		for (User u : users) {

			uDtos.add(userConverter.convertEntityToUsrDto(u));
		}
		return uDtos;
	}

	
	@Override
	public long countOfUser() {
		// with the help of count method fetching the number of user present in database
		long count = userRepository.count();
		return count;
	}

	@Override
	public List<UserDTO> sortUserByName() {
		//sorting user according to name
		List<User> users=	userRepository.findAllSortedByName();
		List<UserDTO> uDtos = new ArrayList<>();
		
		for (User u : users) {

			uDtos.add(userConverter.convertEntityToUsrDto(u));
		}
		return uDtos;
	}


	@Override
	public void assignUserToPet(int uId, int pId) throws ResourceNotFoundException, DataIntegrityViolationException {

		Pet pet = petRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Pet", "id", uId));

		if (pet.getAvailable().equals("Not adopted")) {
			User user = userRepository.findById(uId)
					.orElseThrow(() -> new ResourceNotFoundException("Pet", "id", pId));

			pet.setUser(user);
			pet.setAvailable("Adopted");

			userRepository.save(user);
			petRepository.save(pet);
		}
		else {
			throw new DataNotFoundException("pet is already adopted");
		}
	}


}