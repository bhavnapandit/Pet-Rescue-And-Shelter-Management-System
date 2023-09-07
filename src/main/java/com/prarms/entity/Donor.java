package com.prarms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Donor_details")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Donor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int donorId;
	
	@Column(length=30,nullable=false)
	private String donorName;
	
	@Column(length=30,unique=true)
	private String donoremail;
	
	@Column(length=10,nullable=false,unique=true)
	private String contact;
	
	@Column(length=20,nullable=true)
	private String donation;
	
	@Column(nullable=true)
	private double donationAmount;
	
	@OneToOne
	private Address address;
	
	@Builder
	public Donor(int donorId, String donorName, String donoremail, String contact, String donation,
			double donationAmount, Address address) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.donoremail = donoremail;
		this.contact = contact;
		this.donation = donation;
		this.donationAmount = donationAmount;
		this.address = address;
	}

	
}