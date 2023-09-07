package com.prarms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.prarms.entity.Member;


public interface  MemberRepository extends JpaRepository<Member, Integer>{

	@Query("from Member where memberName=:e")
	List<Member> findMemberByName(@Param("e") String name);

	@Query("from Member where email=:e")
	Optional<Member> findByEmail(@Param("e") String email);
	
	@Query("from Member where  position =:e")
	List<Member> findMemberByPosition( @Param("e") String  position);
	
}
