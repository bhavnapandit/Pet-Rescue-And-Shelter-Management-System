package com.prarms.entity;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Table(name = "Pet_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Pet  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false,length=30)
	private String name;
	
	@Column(nullable = false,length = 30)
	private String breed;
	
	@Column(nullable=false,length=10)
	private String category ;
	
	@Column(nullable = false)
	private LocalDate dateOfRescue;
	
	@Column(nullable = false,length = 20)
	private String available;
	
	@Column(nullable=false)
	private int fund;
	
	@OneToOne //one pet can have only one owner(user)
	User user;
	
	
}