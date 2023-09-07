package com.prarms.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Address  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addId;

	@Column(length = 100, nullable = false)
	private String locality;

	@Column(length = 50, nullable = false)
	private String city;

	@Column(nullable = false)
	private int pincode;

	@Column(length = 30, nullable = false)
	private String state;

	@Builder
	public Address(int addId, String locality, String city, int pincode, String state) {
		super();
		this.addId = addId;
		this.locality = locality;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}

	
}
