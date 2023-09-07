package com.prarms.entity;


import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_details")
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (length = 30 ,nullable = false)
	private String name;
	
	@Column(length = 50 , nullable = false , unique = true )
	private String email;
	
	@Column(length = 50, unique = true , nullable = false)
	private String userName;
	
	@OneToOne //one user can have only one address
	private Address address;
	
	@OneToMany//one user can adopt  many pets
	private List<Pet> pets;


}