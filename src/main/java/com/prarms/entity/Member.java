package com.prarms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Member_details")
@Inheritance(strategy=InheritanceType.JOINED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberId;

	@Column(length = 30, nullable = false)
	private String memberName;

	@Column(length = 50, nullable = false)
	private String position;

	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@Column(length = 10, nullable = false, unique = true)
	private String contactNo;

	@OneToOne // one member can have only one address
	private Address address;


	@Builder
	public Member(int memberId, String memberName, String position, String email, String contactNo, Address address
		) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.position = position;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
		
	}

	

	
}
