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
public class MemberDTO {

	private int memberId;

	@NotNull(message="Name is required")
	@Size(min=2,message="Min 2")
	@Size(max=30,message="Max 30")
	@NotBlank
	private String memberName;

	@NotNull(message="Position is required")
	@Size(min=2,message="Min 3")
	@Size(max=30,message="Max 30")
	@NotBlank
	private String position;

	@Size(max=50,message="Max 50")
	@Email(message="Invalid email")
	private String email;

	@NotNull(message = "Contact is required")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid contact")
	@Size(min=10,message="Min 10")
	@Size(max=10,message="Max 10")
	private String contactNo;
	
	@OneToOne // one member can have only one address
	private Address address;

	
}