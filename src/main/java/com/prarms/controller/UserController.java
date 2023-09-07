package com.prarms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prarms.dto.UserDTO;
import com.prarms.entity.User;
import com.prarms.service.UserService;
import com.prarms.util.UserConverter;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserConverter userConverter;
	
	//saving user
	@PostMapping("/saveUser")
	public UserDTO saveUser(@RequestBody UserDTO usDto) 
	{
		final User usr = userConverter.convertDtoToUsrEntity(usDto);
		return userService.saveUser(usr);
	}
	
	//fetching all the user details
	@GetMapping("/getAllUser")
	public List<UserDTO> getAllDetails(){
		return userService.getAllDetails();
	}
	
	//updating the user details
	
	@PutMapping("/updateUser/{id}")
	public UserDTO updateUser( @RequestBody UserDTO uDto,@PathVariable("id")int uId) {
	final User user = userConverter.convertDtoToUsrEntity(uDto);
	return userService.updateUser(user, uId);
	}
	
	//fetching user by id
	@GetMapping("/getUserById/{id}")
	public UserDTO findUserById(@PathVariable("id")int id){
	return userService.getUserById(id);
	}

	//fetching user by name
	@GetMapping("/getUserByName/{n}")
	public List<UserDTO> findUserByName(@PathVariable("n")String name){
	return userService.getUserByName(name);
	}

	//fetching user by email
	@GetMapping("/getByEmail/{e}")
	public List<UserDTO> findtUserByEmail(@PathVariable("e")String email){
	return userService.getUserByEmail(email);
	}

	
//count the number of user
	@GetMapping("/countOfUsers")
	public String countOfUsers() {
	String msg="Number of users available are :"+userService.countOfUser();

	return msg;
	}

	//sorting it by name
	@GetMapping("/sortUserByName")
	public List<UserDTO> findAllSortedByName(){
	return userService.sortUserByName();
	}

	//assign the user with pet they have adopted
	@PostMapping("/assignUser/{uId}/toPet/{pId}")
	public ResponseEntity<String> assignUserToPet(@PathVariable("uId") int uId, @PathVariable("pId") int pId) {

		userService.assignUserToPet(uId,pId);
		return new ResponseEntity<String>("User with id: " + uId + " adopted pet id " +pId,
				HttpStatus.OK);

	}
}