package com.prarms.serviceTest;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.prarms.entity.Address;
import com.prarms.entity.Member;
import com.prarms.repository.MemberRepository;
import com.prarms.service.MemberService;
@SpringBootTest
class MemberServiceTest {
	@Autowired
	MemberService mbrService;
	@MockBean
	MemberRepository mbrRepository;

	//positive test case
	@Test
	void testSaveMember() {
		
		Address add=Address.builder().locality("Behala").
				city("Kolkata").pincode(700040).state("WestBengal").build();
	
		Member mbr=Member.builder().memberName("Bhavna").position("manager").email("bhavna@gmail.com")
				.contactNo("7044707895").address(add).build();
		
		Mockito.when(mbrRepository.save(mbr)).thenReturn(mbr);
		assertEquals("Bhavna",mbrService.saveMember(mbr).getMemberName());
		
	}

	//positive test case
	@Test
	void testGetMemberById()
	{
		Address add=Address.builder().locality("Behala").
				city("Kolkata").pincode(700040).state("WestBengal").build();
		
		Member mbr= Member.builder().memberName("Bhavna").position("maneger").email("bhavna@gmail.com").
				contactNo("9867543245").address(add).build();
		
		Optional<Member> opMbr=Optional.of(mbr);
		
		Mockito.when(mbrRepository.findById(mbr.getMemberId())).thenReturn(opMbr);
		
		assertTrue (mbrService.getmemberById(mbr.getMemberId()).getEmail().equals("bhavna@gmail.com"));
	}
	
	//negative test case
	@Test
	@DisplayName("Negative test case")
	void testNegativeMemberById() {

		Address add=Address.builder().locality("Behala").
				city("Kolkata").pincode(700040).state("WestBengal").build();
	
		Member mbr= Member.builder().memberName("Bhavna").position("maneger").email("bhavna@gmail.com").
				contactNo("9867543245").address(add).build();
		
		Optional<Member> opMbr=Optional.of(mbr);
		
		Mockito.when(mbrRepository.findById(mbr.getMemberId())).thenReturn(opMbr);
		
		assertTrue (mbrService.getmemberById(mbr.getMemberId()).getEmail().equals("tanu@gmail.com"));
	}
	}