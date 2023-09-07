package com.prarms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.prarms.entity.Pet;

public interface PetRespository extends JpaRepository<Pet, Integer> {

	// method to get pet by name
	@Query("from Pet where name=:n")
	List<Pet> findPetByName(@Param("n") String name);

	// method to get pet by breed
	@Query("from Pet where breed=:b")
	List<Pet> findPetByBreed(@Param("b") String breed);

	// method to get pet by category
	@Query("from Pet where category=:c")
	List<Pet> findPetByCategory(@Param("c") String category);

	// method to sort pet by name
	@Query("from Pet p order by p.name")
	List<Pet> findAllSortedByName();
	
	//method to get pets which are not adopted yet
	@Query("from Pet where available=:a")
	List<Pet> findPetByAvailable(@Param("a") String available);
}
