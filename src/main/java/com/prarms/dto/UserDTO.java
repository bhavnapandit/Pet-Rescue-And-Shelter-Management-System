package com.prarms.dto;



import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.prarms.entity.Address;
import com.prarms.entity.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int id;
	
	@NotNull(message="Name is required")
	@Size(min=2,message="Min 2")
	@Size(max=30,message="Max 30")
	@NotBlank
	private String name;
	
	@Size(max=50,message="Max 50")
	@Email(message="Invalid email")
	private String email;
	
	private String userName;
	
	@OneToOne //one user can have only one address
	private Address address;
	
	@OneToMany
	private List<Pet> pets;
	
	
}