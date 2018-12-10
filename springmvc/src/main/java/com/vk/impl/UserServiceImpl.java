/**
 * 
 */
package com.vk.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vk.dto.UserDTO;
import com.vk.entity.UserEntity;
import com.vk.exception.VKException;
import com.vk.repository.UserRespository;
import com.vk.service.UserService;
import com.vk.util.ObjectMapper;

/**
 * @author Vinay Kumar
 *
 */

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRespository userRespository;
	
	@PersistenceContext
    private EntityManager manager;
	
	@Override
	public UserDTO create(UserDTO userDTO) throws Exception {
		
		if(Objects.isNull(userDTO))
			throw new VKException("user cannot be empty");
		
		if(Objects.isNull(userDTO.getUserName()))
			throw new VKException("user name cannot be empty");
		
		if(Objects.isNull(userDTO.getPassword()))
			throw new VKException("password cannot be empty");
		
		if(Objects.isNull(userDTO.getFirstName()))
			throw new VKException("first name cannot be empty");
		
		if(Objects.isNull(userDTO.getLastName()))
			throw new VKException("last name cannot be empty");
		
		if(Objects.isNull(userDTO.getMobile()))
			throw new VKException("mobile cannot be empty");

		if(Objects.isNull(userDTO.getEmail()))
			throw new VKException("email cannot be empty");
		
		if(Objects.isNull(userDTO.getStatus()))
			throw new VKException("status cannot be empty");
		
		UserEntity userEntity = ObjectMapper.toUserEntity(userDTO);
		userEntity.setCreatedOn(new Date());
		userEntity.setUid(UUID.randomUUID().toString());
		userEntity = userRespository.save(userEntity);
		return ObjectMapper.toUserDTO(userEntity);
	}

	@Override
	public UserDTO update(UserDTO userDTO) throws Exception {

		if(Objects.isNull(userDTO))
			throw new VKException("user cannot be empty");
		
		if(Objects.isNull(userDTO.getUid()))
			throw new VKException("user uid cannot be empty");
		
		if(Objects.isNull(userDTO.getUserName()))
			throw new VKException("user name cannot be empty");
		
		if(Objects.isNull(userDTO.getPassword()))
			throw new VKException("password cannot be empty");
		
		if(Objects.isNull(userDTO.getFirstName()))
			throw new VKException("first name cannot be empty");
		
		if(Objects.isNull(userDTO.getLastName()))
			throw new VKException("last name cannot be empty");
		
		if(Objects.isNull(userDTO.getMobile()))
			throw new VKException("mobile cannot be empty");

		if(Objects.isNull(userDTO.getEmail()))
			throw new VKException("email cannot be empty");
		
		if(Objects.isNull(userDTO.getStatus()))
			throw new VKException("status cannot be empty");
		
		UserEntity userEntity = ObjectMapper.toUserEntity(userDTO);
		UserEntity existingUserEntity = userRespository.findByUid(userDTO.getUid());
		if(existingUserEntity == null)
			throw new VKException("Invalid UID. UID does not exist");
		else {
			userEntity.setId(existingUserEntity.getId());
			userEntity.setCreatedOn(existingUserEntity.getCreatedOn());
			userEntity.setLastUpdatedOn(new Date());
		}
		userEntity = userRespository.save(userEntity);
		return ObjectMapper.toUserDTO(userEntity);
	}
	
	@Transactional
	@Override
	public String delete(UserDTO userDTO) throws Exception {

		if(Objects.isNull(userDTO))
			throw new VKException("user cannot be empty");
		
		if(Objects.isNull(userDTO.getUid()))
			throw new VKException("user uid cannot be empty");
		
		UserEntity existingUserEntity = userRespository.findByUid(userDTO.getUid());
		UserEntity user = existingUserEntity;
		if(existingUserEntity == null)
			throw new VKException("Invalid UID. UID does not exist");
		else
			manager.remove(existingUserEntity);
			return "user "+user.getFirstName()+" deleted successfully";
	}
	
	@Override
	public UserDTO findByUid(String uid) throws Exception {
		
		if(Objects.isNull(uid))
			throw new VKException("user uid cannot be empty");
		
		UserEntity userEntity = userRespository.findByUid(uid);
		if(userEntity == null)
			throw new VKException("Invalid UID. UID does not exist");
		else{
			return ObjectMapper.toUserDTO(userEntity);
		}
	}

	@Override
	public List<UserDTO> getUserList() throws Exception {
		
		List<UserEntity> usersList = new ArrayList<>();
		usersList = userRespository.findAll();
		if(usersList != null && !usersList.isEmpty()) {
			List<UserDTO> usersList1 = ObjectMapper.touserDTOList(usersList);
			return usersList1;
		}else {
			return null;
		}
	}

}
