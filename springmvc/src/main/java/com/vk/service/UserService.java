/**
 * 
 */
package com.vk.service;

import java.util.List;

import com.vk.dto.UserDTO;

/**
 * @author Vinay Kumar
 *
 */
public interface UserService {

	public UserDTO create(UserDTO userDTO) throws Exception;
	
	public UserDTO update(UserDTO userDTO) throws Exception;
	
	public String delete(UserDTO userDTO) throws Exception;
	
	public UserDTO findByUid(String uid) throws Exception;
	
	public List<UserDTO> getUserList() throws Exception;
	
}
