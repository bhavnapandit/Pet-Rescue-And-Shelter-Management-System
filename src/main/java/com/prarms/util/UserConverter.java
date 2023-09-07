package com.prarms.util;



import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.prarms.dto.UserDTO;
import com.prarms.entity.User;



@Component
public class UserConverter {

	//method to convert dto to user entity;
		public User convertDtoToUsrEntity(UserDTO uDto)
		{
			User usr= new User();
			
			if(uDto!=null)
			{
				BeanUtils.copyProperties(uDto, usr);
			}
			return usr;
		}
		
		//method to convert user entity to dto;
		public UserDTO convertEntityToUsrDto(User usr)
		{
			UserDTO uDto= new UserDTO();
			
			if(usr!=null)
			{
				BeanUtils.copyProperties(usr, uDto);
			}
			return uDto;
		}
}