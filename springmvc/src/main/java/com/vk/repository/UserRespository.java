/**
 * 
 */
package com.vk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.entity.UserEntity;

/**
 * @author Vinay Kumar
 *
 */
public interface UserRespository extends JpaRepository<UserEntity, Serializable> {

	public UserEntity findByUid(String uid);
	
	public UserEntity findByUserName(String userName);
	
	public UserEntity findByUserNameAndPassword(String userName,String password);
	
}
