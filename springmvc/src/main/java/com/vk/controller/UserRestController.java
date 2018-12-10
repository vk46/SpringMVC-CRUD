/**
 * 
 */
package com.vk.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vk.dto.UserDTO;
import com.vk.exception.VKException;
import com.vk.service.UserService;

/**
 * @author Vinay Kumar
 *
 */

@RestController
public class UserRestController {

	private static final Logger logger = Logger.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/user/create", method=RequestMethod.POST)
	public UserDTO create(@RequestBody UserDTO userDTO) throws Exception{
		return userService.create(userDTO);
	}
	
	@RequestMapping(value= "/user/update", method=RequestMethod.POST)
	public UserDTO update(@RequestBody UserDTO userDTO) throws Exception{
		return userService.update(userDTO);
	}
	
	@RequestMapping(value= "/user/delete", method=RequestMethod.POST)
	public String delete(@RequestBody UserDTO userDTO) throws Exception{
		return userService.delete(userDTO);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserDTO> getUserList() throws Exception {
		return userService.getUserList();
	}
	
	@ExceptionHandler(VKException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleAppException(VKException ex) {
		ex.printStackTrace();
		logger.error(ex.getStackTrace(),ex);
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleAppException(Exception ex) {
	  ex.printStackTrace();	
	  logger.error(ex.getStackTrace(),ex);
	  return ex.getMessage();
	}
}
