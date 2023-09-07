package com.prarms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.prarms.dto.MemberDTO;
import com.prarms.entity.Member;



@Component
public class MemberConverter {

	//method to convert dto to member entity;
		public Member convertDtoToMembrEntity(MemberDTO mDto)
		{
			Member membr= new Member();
			
			if(mDto!=null)
			{
				BeanUtils.copyProperties(mDto, membr);
			}
			return membr;
		}
		
		//method to convert member entity to dto;
		public MemberDTO convertEntityToMembrDto(Member membr)
		{
			MemberDTO mDto= new MemberDTO();
			
			if(membr!=null)
			{
				BeanUtils.copyProperties(membr, mDto);
			}
			return mDto;
		}
}