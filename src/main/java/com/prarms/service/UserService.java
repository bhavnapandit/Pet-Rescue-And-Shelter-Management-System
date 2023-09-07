package com.prarms.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.prarms.dto.UserDTO;
import com.prarms.entity.User;
import com.prarms.exceptions.ResourceNotFoundException;

public interface UserService {
// method used to save user details into the database
UserDTO saveUser(User user);

// method to get all the details of user present in database
List<UserDTO> getAllDetails();

// method to update user details
UserDTO updateUser(User user, int uId);

// method to get user by id
UserDTO getUserById(int id);

// method to get users by name
List<UserDTO> getUserByName(String name);

// method to get users by email
List<UserDTO> getUserByEmail(String email);


// method to count number of users
long countOfUser();

// method to sort users by name
List<UserDTO> sortUserByName();

void assignUserToPet(int uId, int pId) throws ResourceNotFoundException, DataIntegrityViolationException;

}