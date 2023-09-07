package com.prarms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.prarms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// method to get user by name
	@Query("from User where name=:n")
	List<User> findUserByName(@Param("n") String name);

	// method to get user by email
	@Query("from User where email=:e")
	List<User> findUserByEmail(@Param("e") String email);

	// method to sort user by name
	@Query("from User p order by p.name")
	List<User> findAllSortedByName();

}