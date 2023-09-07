package com.prarms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prarms.dto.MemberDTO;
import com.prarms.entity.Member;
import com.prarms.exceptions.DataNotFoundException;
import com.prarms.exceptions.ResourceNotFoundException;
import com.prarms.repository.AddressRepository;
import com.prarms.repository.MemberRepository;
import com.prarms.service.MemberService;
import com.prarms.util.MemberConverter;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberConverter memberConverter;

	@Autowired
	AddressRepository addRepository;



	@Override
	public MemberDTO saveMember(Member membr) {

		addRepository.save(membr.getAddress());
		memberRepository.save(membr);// saving the object to the database
		MemberDTO mDTo = memberConverter.convertEntityToMembrDto(membr);

		return mDTo;
	}

	@Override
	public List<MemberDTO> getAllDetails() throws DataNotFoundException{
		List<Member> mbrs = memberRepository.findAll();// to fetch all details

		// checking if data is present or not and handling the exception
		if (mbrs.isEmpty()) {
			throw new DataNotFoundException("Not data found in Member tables!!");
		}

		List<MemberDTO> mDtos = new ArrayList<>();

		for (Member m : mbrs) {

			mDtos.add(memberConverter.convertEntityToMembrDto(m));// converting into dto
		}
		return mDtos;

	}

	@Override
	public MemberDTO updateMember(Member mbr, int mId)throws ResourceNotFoundException {
		// fetch the member details using the id and throw an error if that id is not found
		Member existMember = memberRepository.findById(mId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet", "id", "pId"));

		// updating the existing member details with new updated details
		existMember.setMemberName(mbr.getMemberName());
		existMember.setPosition(mbr.getPosition());
		existMember.setEmail(mbr.getEmail());
		existMember.setContactNo(mbr.getContactNo());
		existMember.setAddress(mbr.getAddress());
		// saving the changes made
		memberRepository.save(existMember);

		return memberConverter.convertEntityToMembrDto(existMember);
	}

	@Override
	public MemberDTO getmemberById(int mId) throws ResourceNotFoundException{
		// if member with that id is not found it will throw an error
		Member mbr = memberRepository.findById(mId)
				.orElseThrow(() -> new ResourceNotFoundException("Member", "id", mId));
		return memberConverter.convertEntityToMembrDto(mbr);
	}

	@Override
	public List<MemberDTO> getMemberByName(String name) throws DataNotFoundException{
		List<Member> members = memberRepository.findMemberByName(name);
		
		// checking if data is present or not and handling the exception
		if (members.isEmpty()) {
			throw new DataNotFoundException("Not data with that name found in Member tables!!");
		}

		List<MemberDTO> mDtos = new ArrayList<>();
		for (Member m : members) {
			mDtos.add(memberConverter.convertEntityToMembrDto(m));
		}
		return mDtos;
	}

	@Override
	public MemberDTO getMemberByEmail(String email) throws ResourceNotFoundException {
		
		//if member with that error is not found it will throw an error
		Member mbr = memberRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Member", "email", email));
		
		return memberConverter.convertEntityToMembrDto(mbr);

	}

	@Override
	public List<MemberDTO> getMemberByPosition(String position) {
		//fetching data by position 
		List<Member> mbrs = memberRepository.findMemberByPosition(position);
		List<MemberDTO> mbrDtos = new ArrayList<>();
		for (Member s : mbrs) {
			mbrDtos.add(memberConverter.convertEntityToMembrDto(s));
		}
		return mbrDtos;
	}
}
