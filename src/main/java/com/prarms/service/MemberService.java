package com.prarms.service;

import java.util.List;


import com.prarms.dto.MemberDTO;
import com.prarms.entity.Member;


public interface MemberService {

	// method to save member
	MemberDTO saveMember(Member membr);

	// method to get all the details of members
	List<MemberDTO> getAllDetails();

	// method to update details
	MemberDTO updateMember(Member mbr, int mId);

	// method to fetch details using member id
	MemberDTO getmemberById(int mId);

	// method to fetch member details using name
	List<MemberDTO> getMemberByName(String name);

	// method use to fetch member details using email
	MemberDTO getMemberByEmail(String email);
	
	//method use to fetch Member details by position
	List<MemberDTO> getMemberByPosition(String  position);

}
