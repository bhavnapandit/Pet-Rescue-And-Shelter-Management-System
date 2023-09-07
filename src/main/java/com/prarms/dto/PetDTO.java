package com.prarms.dto;



import java.time.LocalDate;


import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.prarms.entity.User;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PetDTO  {
	
	
	private int id;
	
	@NotNull(message="Name is required")
	@Size(min=3, message="Min. 3 characters required")
	@Size( max=30, message="Max. 30 characters allowed")
	@NotBlank(message="Please enter correct name")
	private String name;
	
	@NotNull(message="Breed is required")
	@Size(min=3, message="Min. 3 characters required")
	@Size( max=30, message="Max. 30 characters allowed")
	@NotBlank(message="Please enter correct breed")
	private String breed;
	
	@NotNull(message="Category is required")
	@Size(min=3, message="Min. 3 characters required")
	@Size( max=10, message="Max. 10 characters allowed")
	private String category ;
	
	@NotNull(message="DOR is required")
	private LocalDate dateOfRescue;
	
	@NotNull(message="food is required")
	@Size( max=20, message="Max. 20 characters allowed")
	private String available;
	
	
	@NotNull(message="fund is required")
	private int fund;
	
	@OneToOne
	User user;
	
}