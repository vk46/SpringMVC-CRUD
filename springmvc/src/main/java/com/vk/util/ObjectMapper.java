/**
 * 
 */
package com.vk.util;

import java.util.ArrayList;
import java.util.List;

import com.vk.dto.UserDTO;
import com.vk.entity.UserEntity;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author Vinay Kumar
 *
 */
public class ObjectMapper {

	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	public static UserEntity toUserEntity(UserDTO userDTO) {
		BoundMapperFacade<UserDTO, UserEntity> boundMapper = mapperFactory.getMapperFacade(UserDTO.class, UserEntity.class);
		return boundMapper.map(userDTO);
	}
	
	public static UserDTO toUserDTO(UserEntity userEntity) {
		mapperFactory.classMap(UserEntity.class, UserDTO.class).byDefault().register();
		BoundMapperFacade<UserEntity, UserDTO> boundMapper = mapperFactory.getMapperFacade(UserEntity.class,UserDTO.class);
		return boundMapper.map(userEntity);
	}
	
	public static List<UserDTO> touserDTOList(List<UserEntity> userEntityList) {
		mapperFactory.classMap(UserEntity.class, UserDTO.class).byDefault().register();
		BoundMapperFacade<UserEntity, UserDTO> boundMapper = mapperFactory.getMapperFacade(UserEntity.class, UserDTO.class);
		List <UserDTO> userDTOList = new ArrayList<>(userEntityList.size());
		for(UserEntity userEntity : userEntityList) {
			userDTOList.add(boundMapper.map(userEntity));
		}
		return userDTOList;
	}
	
}
