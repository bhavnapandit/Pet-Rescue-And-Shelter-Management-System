package com.prarms.dto;


import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.prarms.entity.Address;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DonorDTO {
	
	
	private int donorId;
	
	@NotNull(message="Name is required")
	@Size(min=2, message="Min 2 characters required")
	@Size(max=30, message="Max. 30 characters required")
	@NotBlank(message="Please enter correct name")
	private String donorName;
	
	@Size(max=50,message="Max 50 chracters allowed")
	@Email(message="Invalid email")
	private String donoremail;
	
	@NotNull(message="Contact is required")
	@Pattern(regexp="[6789]{1}[0-9]{9}",message="Invalid contact details")
	@Size(min=10,max=10,message="min. and max. 10 digits allowed")
	private String contact;
	
	@Size(max=20,message="Max 20 chracters allowed")
	private String donation;
	
	@NotNull(message="DonationAmount is required")
	private double donationAmount;
	
	@OneToOne
	private Address address;
}